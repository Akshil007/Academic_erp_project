let student_form = document.getElementById('student-validation');


//var opt = sel.options[sel.selectedIndex];
//console.log(opt);
if(!student_form){
    console.log("aSfvxzv")
}
student_form.addEventListener('submit', async (e) => {
  e.preventDefault();
  e.stopPropagation();
      //console.log(opt.value);
   // console.log(document.getElementById('department').value);
    document.getElementById("login-alert").style.display = "none";
    document.getElementById("login-success").style.display = "none";
    document.getElementById("password-fail").style.display = "none";
        if (student_form.checkValidity() === true) {
            let check_salary =  document.getElementById("salary").value;
            if(check_salary>5000) {
                document.getElementById("Invalid_salary").style.display="none";
                let p = document.getElementById('password').value;
                let cp = document.getElementById('Cpassword').value;
                let result = p.localeCompare(cp);
                console.log(result);
                if (result === 0) {
                    document.getElementById("password-fail").style.display = "none";
                    console.log("here");
                    let response = await fetch('api/employee/add', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json;charset=utf-8'
                        },
                        body: JSON.stringify({
                            first_name: document.getElementById('first_name').value,
                            last_name: document.getElementById('last_name').value,
                            email: document.getElementById('email').value,
                            salary: document.getElementById('salary').value,
                            department: document.getElementById('department').value,
                            password: document.getElementById('password').value,
                        })
                    }).then(
                        response => {
                            console.log(response["status"]);
                            if (response['status'] === 200) {
                                document.getElementById("login-alert").style.display = "none";
                                document.getElementById("login-success").style.display = "block";


                            } else {
                                document.getElementById("login-success").style.display = "none";
                                document.getElementById("login-alert").style.display = "block";

                            }
                        }
                    );
                } else {
                    console.log("password incorrect");
                    document.getElementById("password-fail").style.display = "block";
                }
            }
            else
            {
                document.getElementById("Invalid_salary").style.display="block";
            }
        } else {
            student_form.classList.add('was-validated');
        }



});

