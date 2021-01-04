let form1 = document.getElementById("f1");

form1.addEventListener('submit', async (e) =>{

    e.preventDefault();
    e.stopPropagation();
    let localid=document.getElementById('disid').value;
    if(localStorage.getItem("employee_id")!==localid) {
    let check_salary =  document.getElementById("salary").value;
    if(check_salary>5000) {
        let response = await fetch('api/employee/modify', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify({
                employee_id: document.getElementById('disid').value,
                salary: document.getElementById("salary").value,

            })
        }).then(
            response => {
                if (response['status'] === 200) {
                    console.log(200);
                    alert("Salary changed Successfully");
                } else if (response['status'] === 201) {
                    console.log(201);
                    alert("Employee Id is Not Found");
                } else {
                    alert("Somthing went Wrong.");
                }
            }
        );
    }
    else
    {
        alert("You can not Enter your Own ID!!!");
    }
}
else
{
    alert("Salary Requirements didn't Match.");
}
});

