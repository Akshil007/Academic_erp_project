let form = document.getElementById("Disburse-Salary");
let form1 = document.getElementById("f1");
window.onload = myFunction;
if(!form){
    console.log("aSfvxzv")
}
 
form.addEventListener('submit', async (e) =>{

    e.preventDefault();
    e.stopPropagation();

    var checkboxes = document.querySelectorAll('input[type="checkbox"]:checked');

    let ids = [];
    checkboxes.forEach((checkbox) => {
        var employee = {};
        employee["employee_id"] = checkbox.id;
        ids.push(employee);
    });
    let response = await fetch('api/employee/delete', {
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
        let response = await fetch('api/employee/delete', {
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
                    alert("Employee ID is Not Found !!!");
                } else {
                    console.log("isko kya hua bhai");
                }
            }
        );
        window.location.reload();
    }
    else
    {
        alert("You can not Enter Your Own Id !!!");
    }
});

async function myFunction() {
    let employees = await  fetch('api/employee/getAllEmployees', {
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

    for(let i = 0; i < employees.length; i++) {
        empDisplay.innerHTML += '<tr> ' +
            '<td> ' +
            '<div class="custom-control custom-checkbox">' +
            ' <input type="checkbox" class="custom-control-input" id="' + employees[i]["employee_id"] + '"> ' +
            '<label class="custom-control-label" for="' +employees[i]["employee_id"] + '"> ' +employees[i]["employee_id"]+
            ' </label>' +
            ' </div> ' +
            ' </td>' +
            '<td>' + employees[i]["first_name"]+" "+employees[i]["last_name"] + '</td>' +
            '<td>' + employees[i]["email"] + '</td>' +
            '<td>' + employees[i]["department"] + '</td>' +

            '<td>' + employees[i]["salary"] + '</td> ' +
            '</tr>';
    }
}
