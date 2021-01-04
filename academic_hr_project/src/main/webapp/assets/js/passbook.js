let passbook = document.getElementById('p');
window.onload = myFunction;

 async function myFunction() {

     console.log("hello");
     document.getElementById('name').value = localStorage.getItem("name");
     document.getElementById('email').value = localStorage.getItem("email");
     document.getElementById('salary').value = localStorage.getItem("salary");
     document.getElementById('department').value = localStorage.getItem("department");
     document.getElementById('id').value = localStorage.getItem("employee_id");

     let response = await fetch("api/salary/getSalaries", {
         method: 'POST',
         headers: {
             'Content-Type': 'application/json;charset=utf-8'
         },
         body: JSON.stringify({
             employee_id: localStorage.getItem("employee_id"),
         })
     });

     let salaries = await response.json();
     console.log(salaries);

     let table_data = document.getElementById("t01");
     table_data.innerHTML = '';
     let tr_data = document.createElement('tr');
     tr_data.innerHTML = '<th >  EMP ID  </th>\n' +
         '            <th>   Name  </th>\n' +
         '            <th> Amount  </th>\n' +
         '            <th> Date  </th>\n' +
         '            <th> Description  </th>';
     table_data.appendChild(tr_data);
     if (salaries.length === 0) {
         localStorage.setItem("clip_amount", "NAN");
         localStorage.setItem("clip_date", "NAN");
         localStorage.setItem("clip_description", "NAN");
     } else {
         let json = (salaries[0]["payment_date"]).toString();
         json = "\"" + json + "\"";
         let dateStr = JSON.parse(json);
         let date = new Date(dateStr);
         date = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
         localStorage.setItem("clip_amount", salaries[0]['amount']);
         localStorage.setItem("clip_date", date);
         localStorage.setItem("clip_description", salaries[0]['description']);
         for (let i = 0; i < salaries.length; i++) {
             let json = (salaries[i]["payment_date"]).toString();
             json = "\"" + json + "\"";
             let dateStr = JSON.parse(json);
             let date = new Date(dateStr);
             date = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
             let tr_data = document.createElement('tr');
             tr_data.innerHTML = '<td>' + salaries[i]['employee']['employee_id'] + '</td>\n' +

                 '            <td>' + salaries[i]['employee']['first_name'] + " " + salaries[i]['employee']['last_name'] + '</td>\n' +
                 '            <td>' + salaries[i]['amount'] + '</td>\n' +
                 '            <td>' + date + '</td>\n' +
                 '            <td>' + salaries[i]['description'] + '</td>';
             table_data.appendChild(tr_data);
         }
     }
 }
passbook.addEventListener('submit', async (e) =>{


    var textArea = document.createElement("textarea");
    textArea.innerText = " Employee Id:"+localStorage.getItem("employee_id")
        +"\t Name:"+localStorage.getItem("name")
        +"\t Amount:"+localStorage.getItem("clip_amount")
        +"\t Date:"+localStorage.getItem("clip_date")
        +"\t Description:"+localStorage.getItem("clip_description");

    // Avoid scrolling to bottom
    textArea.style.top = "0";
    textArea.style.left = "0";
    textArea.style.position = "fixed";

    document.body.appendChild(textArea);
    textArea.focus();
    textArea.select();

    try {
        var successful = document.execCommand('copy');
        var msg = successful ? 'successful' : 'unsuccessful';
        console.log('Fallback: Copying text command was ' + msg);
    } catch (err) {
        console.error('Fallback: Oops, unable to copy', err);
    }


});
