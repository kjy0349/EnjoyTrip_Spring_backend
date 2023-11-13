// 로그인
document.querySelector("#loginBtn").addEventListener("click", (e) => {
  console.log("나 눌렸어!!");
  let sendData = {
    userid: document.querySelector("#userid").value,
    userpassword: document.querySelector("#userpassword").value,
  };
  localStorage.setItem("loginData", JSON.stringify(sendData));
  //   location.reload();

  console.log("로그인 성공!");
  // window.open("../index.html", "_blank");
  location.href = "../../index.html";
});

document.querySelector("#DeleteBtn").addEventListener("click", (e) => {
  alert("회원 탈퇴!");
  localStorage.removeItem("loginData");
  location.href = "../../index.html";
});
