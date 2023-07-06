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

function handleButtonClick() {
    if (isLoggedIn) {
        openModal(); // 로그인 상태면 모달 열기
    } else {
        alert("로그인을 하거나 회원가입을 진행해주세요."); // 로그아웃 상태면 알림 메시지 보여주기
    }
}

// 모달 열기
function openModal() {
    var modal = document.getElementById("myModal");
    modal.style.display = "block";
}

// 모달 닫기
function closeModal() {
    var modal = document.getElementById("myModal");
    modal.style.display = "none";
}

// 포스트 제출
function submitPost() {
    var postContent = document.getElementById("postTextArea").value;
    // 여기서 포스트를 처리하거나 전송하는 로직을 구현합니다.
    console.log("포스트 내용:", postContent);
    window.location.reload();
}

// Function to update the like count display for a specific post
function updateLikeCount(postId, likeCnt) {
    const likeCntElement = document.querySelector(`#post-${postId} .like-cnt`);
    likeCntElement.innerText = likeCnt.toString();
}

// Function to update the dislike count display for a specific post
function updateHateCount(postId, hateCnt) {
    const hateCntElement = document.querySelector(`#post-${postId} .hate-cnt`);
    hateCntElement.innerText = hateCnt.toString();
}

// Function to handle the like button click for a specific post
function handleLikeButtonClick(postId, likeButton) {
    if (likeButton.innerText === '좋아요') {
        fetch(`/api/posts/${postId}/like`)
            .then(response => response.json())
            .then(data => {
                const likeCnt = data.likeCnt;
                updateLikeCnt(postId, likeCnt);
            })
            .catch(error => {
                console.log('Error:', error);
            });

        likeButton.innerText = '좋아요 취소';
        console.log('좋아요');
    } else {
        fetch(`/api/posts/${postId}/like`)
            .then(response => response.json())
            .then(data => {
                const likeCnt = data.likeCnt;
                updateLikeCnt(postId, likeCnt);
            })
            .catch(error => {
                console.log('Error:', error);
            });

        likeButton.innerText = '좋아요';
        console.log('좋아요 취소');
    }
}

// Function to handle the dislike button click for a specific post
function handleHateButtonClick(postId, hateButton) {
    if (hateButton.innerText === '싫어요') {
        fetch(`/api/posts/{post_id}/hate`)
            .then(response => response.json())
            .then(data => {
                const hateCnt = data.hateCnt;
                updateHateCnt(postId, hateCnt);
            })
            .catch(error => {
                console.log('Error:', error);
            });

        hateButton.innerText = '싫어요 취소';
        console.log('싫어요');
    } else {
        fetch(`/api/posts/{post_id}/hate`)
            .then(response => response.json())
            .then(data => {
                const hateCnt = data.hateCnt;
                updateHateCnt(postId, hateCnt);
            })
            .catch(error => {
                console.log('Error:', error);
            });

        hateButton.innerText = '싫어요';
        console.log('싫어요 취소');
    }
}

// Attach event listeners to the like and dislike buttons for each post
const postElements = document.querySelectorAll('.post');
postElements.forEach(postElement => {
    const postId = postElement.id.replace('post-', '');
    const likeButton = postElement.querySelector('.like-button');
    const hateButton = postElement.querySelector('.hate-button');

    likeButton.addEventListener('click', function() {
        handleLikeButtonClick(postId, likeButton);
    });

    hateButton.addEventListener('click', function() {
        handleHateButtonClick(postId, hateButton);
    });
});