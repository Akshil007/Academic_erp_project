let form = document.getElementById("Disburse-Salary");
let form1 = document.getElementById("f1");
window.onload = myFunction;

async function myFunction() {
  let empDisplay = document.getElementById("employee-search");
    let response = await fetch('api/salary/disbursed');
    let salaries = await response.json();
        console.log(salaries);
//    let tr_data = document.createElement('tr');
//           tr_data.innerHTML = '<th >  Select  </th>\n' +
//               '            <th>   EMP ID  </th>\n' +
//                '            <th>   Firstname  </th>\n' +
//                '            <th> lastname  </th>\n' +
//                '            <th> Department  </th>\n' +
//                '            <th> Salary  </th>';
//            table_data.appendChild(tr_data);
    for(let i = 0; i < salaries.length; i++) {
        let json=(salaries[i]["payment_date"]).toString();
        json="\""+json+"\"";
        let dateStr = JSON.parse(json);
        let date = new Date(dateStr);
        date=date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
        empDisplay.innerHTML += '<tr> ' +
            '<td>' + salaries[i]["employee"]["employee_id"] + '</td>' +
            '<td>' + salaries[i]["employee"]["first_name"]+" "+salaries[i]["employee"]["last_name"] + '</td>' +
            '<td>' + salaries[i]["amount"] + '</td>' +
            '<td>' + date + '</td>' +
            '<td>' + salaries[i]["description"] + '</td> ' +
            '</tr>';
    }
 }
