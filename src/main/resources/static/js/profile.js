let cookie = document.cookie.split(';').reduce((cookieObject, cookieString) => {
    let splitCookie = cookieString.split('=')
    try {
        cookieObject[splitCookie[0].trim()] = decodeURIComponent(splitCookie[1])
    } catch (error) {
        cookieObject[splitCookie[0].trim()] = splitCookie[1]
    }
    return cookieObject
}, [])

if(cookie.email == "-1")
    window.location.href = "/";

$.ajax({
    url: "http://localhost:8080/api/v1/auth/about/" + cookie.email,
    type: "GET",
    headers: {"Authorization": cookie.token},
    success: function (data) {
        switch (data.role) {
            case "ADMIN": {
                $.ajax({
                    url: "http://localhost:8080/admin",
                    type: "GET",
                    headers: {"Authorization": cookie.token},
                    success: function (data) {
                        $("#mainContainer").append(data);
                    }
                });
                break;
            }
            case "DOCTOR": {
                $.ajax({
                    url: "http://localhost:8080/doctor",
                    type: "GET",
                    headers: {"Authorization": cookie.token},
                    success: function (data) {
                        $("#mainContainer").append(data);
                    }
                });
                break;
            }
            case "PATIENT": {
                $.ajax({
                    url: "http://localhost:8080/patient",
                    type: "GET",
                    headers: {"Authorization": cookie.token},
                    success: function (data) {
                        $("#mainContainer").append(data);
                    }
                });
                break;
            }
        }
    }
});

function logout (){
    $.ajax({
        url: "http://localhost:8080/patient",
        type: "GET",
        headers: {"Authorization": cookie.token},
        success: function (data) {
            $("#mainContainer").append(data);
        }
    });
    document.cookie="token=-1";
    document.cookie="email=-1";
    window.location.href = "/";
}