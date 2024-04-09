// 체크박스 클릭시 id 가져오기

// 화면의 중복요소에 이벤트 작성
// 1) 전체요소 가져오기
// document.querySelectorAll('[name="completed"]').addEventListener("click", (e) => {});

// 2) 이벤트 전파 => 부모요소가 감지
document.querySelector(".list-group").addEventListener("click", (e) => {
  console.log("이벤트가 발생한 대상 " + e.target);
  console.log("이벤트가 발생한 대상 value " + e.target.value);
  console.log("이벤트를 감지한 대상 " + e.currentTarget);

  //   location.href = "/todo/update?id=" + e.target.value; ==> Get방식
});
