let cookie = document.cookie.split(';').reduce((cookieObject, cookieString) => {
    let splitCookie = cookieString.split('=')
    try {
        cookieObject[splitCookie[0].trim()] = decodeURIComponent(splitCookie[1])
    } catch (error) {
        cookieObject[splitCookie[0].trim()] = splitCookie[1]
    }
    return cookieObject
}, [])

if(cookie.email != "-1")
    window.location.href = "/profile";


$("#logInForm").submit(function (event) {
    let email = document.getElementById("exampleInputEmail1").value;
    let password = document.getElementById("exampleInputPassword1").value;
    let firstName = document.getElementById("exampleInputFirstName").value;
    let lastName = document.getElementById("exampleInputLastName").value;
    let input = {
        "email": email,
        "password": password,
        "firstName": firstName,
        "lastName": lastName
    }

    fetch("http://localhost:8080/api/v1/auth/registration", {
        method: 'POST',
        body: JSON.stringify(input),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then((response) => {return response.json()
    }).then((data) => {
        if (data.token === undefined){
            $("#emailError").html(data.message);
            $("#emailError").removeAttr("hidden");
            $("#emailError").attr("visible");
        }
        else
        {
            document.cookie = "token="+ data.token+";";
            document.cookie = "email="+ data.email+";";
            location.href = '/profile'
        }
    }).catch((err) => {
        console.log("error");
        console.log(err);
    })

    event.preventDefault();
});


