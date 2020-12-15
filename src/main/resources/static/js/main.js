let cookie = document.cookie.split(';').reduce((cookieObject, cookieString) => {
    let splitCookie = cookieString.split('=')
    try {
        cookieObject[splitCookie[0].trim()] = decodeURIComponent(splitCookie[1])
    } catch (error) {
        cookieObject[splitCookie[0].trim()] = splitCookie[1]
    }
    return cookieObject
}, [])

//if(cookie.email != "-1")
//    window.location.href = "/profile";


$("#logInForm").submit(function (event) {
    let email = document.getElementById("exampleInputEmail1").value;
    let password = document.getElementById("exampleInputPassword1").value;
    let input = {
        "email": email,
        "password": password
    }



    fetch("http://localhost:8080/api/v1/auth/login", {
        method: 'POST',
        body: JSON.stringify(input),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then((response) => {return response.json()
    }).then((data) => {
        if (data.token === undefined){
            $("#passwordError").html(data.message);
            $("#passwordError").removeAttr("hidden");
            $("#passwordError").attr("visible");

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


