let login_form = document.getElementById('login-validation');
//window.onload = fetch_info;

login_form.addEventListener('submit', async (e) => {
   e.preventDefault();
   e.stopPropagation();

   if(login_form.checkValidity() === true) {
       let response = await  fetch('api/employee/login', {
           method: 'POST',
           headers: {
               'Content-Type': 'application/json;charset=utf-8'
           },
           body: JSON.stringify({
               email: document.getElementById('email').value,
               password: document.getElementById('password').value,
           })
       }).then(
           response => {
               if(response["status"]===200){
                   //console.log("hello");
                   location.href="disburse.html";
               }
               else if(response["status"] === 201)
               {
                   location.href="passbook_normal.html";
               }
               else{
                   document.getElementById('login-alert').style.display = "block";
               }
               return response.json();
           }
       ).then (
           responsedata => {
               localStorage.setItem("employee_id", responsedata.employee_id);
               localStorage.setItem("name", responsedata.first_name + " " + responsedata.last_name);
               localStorage.setItem("salary", responsedata.salary);
               localStorage.setItem("department", responsedata.department);
               localStorage.setItem("email", responsedata.email);
           },
           error => {
               console.error("Error Occurred !!", error);
           }
       );
   }else{
       login_form.classList.add('was-validated');
   }
});
//            .then(
//        console.log("hii");
//        localStorage.setItem("employee_id", response.employee_id);
//        localStorage.setItem("name", response.first_name + " " + response.last_name);
//        // localStorage.setItem("title", response.title);
//        localStorage.setItem("department", response.department);
//        localStorage.setItem("salary",response.salary);
//        console.log(localStorage.getItem("name"));
//    }else{
//        login_form.classList.add('was-validated');
//    }
// });


// async function fetch_info(){
//
//     let response = await fetch("api/employee/departments");
//     let departments = await response.json();
//     console.log(departments);
//     let department_option = document.getElementById('department');
//     department_option.innerHTML = '<option value=""> Choose...</option>';
//
//     for(let i = 0 ; i<departments.length ; i++){
//         department_option.innerHTML += '<option value="'+departments[i]+'">'+departments[i]+'</option>';
//     }
// }

