// document.querySelector("#logout").addEventListener("click", function () {});

// console.log(document.getElementById("beforelogin"));
// 로그인 성공시 화면 전환
const ReceiveData = JSON.parse(localStorage.getItem("loginData"));
console.log(ReceiveData);

if (ReceiveData) {
  if (ReceiveData.userid == "ssafy" && ReceiveData.userpassword == "1234") {
    console.log(document.querySelector("#beforelogin"));
    document.querySelector("#beforelogin").style.display = "none";
    document.querySelector("#afterlogin").style.display = "flex";
  }
}

