const isLoggedIn = false; // 로그인 여부를 true 또는 false로 설정

if (isLoggedIn) {
    document.querySelector('.login-button').style.display = 'none';
    document.querySelector('.signup-button').style.display = 'none';
    document.querySelector('.logout-button').style.display = 'inline-block';
    document.querySelector('.my-page-button').style.display = 'inline-block';
} else {
    document.querySelector('.login-button').style.display = 'inline-block';
    document.querySelector('.signup-button').style.display = 'inline-block';
    document.querySelector('.logout-button').style.display = 'none';
    document.querySelector('.my-page-button').style.display = 'none';
}

// 좋아요 버튼 클릭 이벤트 처리
const likeButton = document.querySelector('.like-button');
likeButton.addEventListener('click', function() {
    // 좋아요 버튼이 클릭되었을 때 동작할 코드 작성
    console.log('좋아요');
});

// 싫어요 버튼 클릭 이벤트 처리
const hateButton = document.querySelector('.hate-button');
hateButton.addEventListener('click', function() {
    // 싫어요 버튼이 클릭되었을 때 동작할 코드 작성
    console.log('싫어요');
});
