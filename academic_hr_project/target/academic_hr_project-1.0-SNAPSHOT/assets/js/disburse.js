let form = document.getElementById("Disburse-Salary");
let form1 = document.getElementById("f1");
window.onload = myFunction();
if(!form){
    console.log("aSfvxzv")
}
 
form.addEventListener('submit', async (e) =>{

    e.preventDefault();
    e.stopPropagation();

    var checkboxes = document.querySelectorAll('input[type="checkbox"]:checked');

    let ids = [];
    // let sal = document.getElementById('amount').value;
    // let des = document.getElementById('description').value;
    checkboxes.forEach((checkbox) => {
        var employee = {};
        employee["employee_id"] = checkbox.id;

        // var employee_sal = {};
        // employee_sal["employee"] = employee
        // employee_sal["amount"] = sal
        // employee_sal["description"] = des
        ids.push(employee);
    });
    console.log(ids);
    // let response2 = await fetch("api/salary/disburse",{
    //     method: 'POST',
    //     headers: {
    //         'Content-Type': 'application/json;charset=utf-8'
    //     },
    //     body: JSON.stringify({
    //         employee_id: 71
    //     })
    // });
    let response = await fetch('api/salary/disburse', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(ids)
    }).then(
        response => {
            if(response['status'] === 200){
                console.log("sab badhiya hai");
            }
            else {
                console.log("isko kya hua bhai");
            }
        }
    );
    window.location.reload();
});


form1.addEventListener('submit', async (e) =>{

    e.preventDefault();
    e.stopPropagation();
    let ids=[]
    let localid=document.getElementById('disid').value;
    if(localStorage.getItem("employee_id")!==localid) {
        var employee = {};
        employee["employee_id"] = localid;
        ids.push(employee);
        console.log(ids);
        let response = await fetch('api/salary/disburse', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify(ids)
        }).then(
            response => {
                if (response['status'] === 200) {
                    console.log("sab badhiya hai");
                } else if (response['status'] === 201) {
                    alert("Employee ID is Wrong.");
                } else {
                    console.log("isko kya hua bhai");
                }
            }
        );
        window.location.reload();
    }
    else
    {
        alert("You can not Enter your own Id !!!");
    }

});
async function myFunction() {
    let employees = await  fetch('api/employee/getEmployees', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify({
            employee_id: localStorage.getItem("employee_id"),
        })
    }).then(
        response => {
            return response.json();
        }
    ).then (
        data => {
            return data;
        },
        error => {
            console.error("Error Occurred !!", error);
        }
    );
    console.log(employees);

  let empDisplay = document.getElementById("employee-search");
        
//    let tr_data = document.createElement('tr');
//           tr_data.innerHTML = '<th >  Select  </th>\n' +
//               '            <th>   EMP ID  </th>\n' +
//                '            <th>   Firstname  </th>\n' +
//                '            <th> lastname  </th>\n' +
//                '            <th> Department  </th>\n' +
//                '            <th> Salary  </th>';
//            table_data.appendChild(tr_data);
    for(let i = 0; i < employees.length; i++) {
        empDisplay.innerHTML += '<tr> ' +
            '<td> ' +
            '<div class="custom-control custom-checkbox">' +
            ' <input type="checkbox" class="custom-control-input" id="' + employees[i]["employee_id"] + '"> ' +
            '<label class="custom-control-label" for="' +employees[i]["employee_id"] + '"> ' +employees[i]["employee_id"]+
            ' </label>' +
            ' </div> ' +
            ' </td>' +
            '<td>' + employees[i]["first_name"] + '</td>' +
            '<td>' + employees[i]["last_name"] + '</td>' +
            '<td>' + employees[i]["department"] + '</td>' +
            
            '<td>' + employees[i]["salary"] + '</td> ' +
            '</tr>';
    }
 }
