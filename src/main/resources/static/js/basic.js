function storeTokenInCookie(token) {
    document.cookie = `Authorization=${token}; path=/`;
}

function updateLoginStatus() {
    const cookie = document.cookie;
    const isLoggedIn = cookie.includes('Authorization');

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
}

function handleLoginSuccess(token) {
    storeTokenInCookie(token);
    updateLoginStatus();
}

function goToDetail(postId) {
    window.location.href = 'detail.html?postId=' + postId;
}

// function handleButtonClick() {
//     if (isLoggedIn) {
//         openModal(); // 로그인 상태면 모달 열기
//     } else {
//         alert("로그인을 하거나 회원가입을 진행해주세요."); // 로그인 상태가 아니면 알림 메시지 보여주기
//     }
// }

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

function submitPost() {
    var postContent = document.getElementById("postTextArea").value;
    var token = getToken();

    if (token) {
        fetch('/api/posts', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization' : token
            },
            body: JSON.stringify({
                content: postContent
            })
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('포스트 생성 실패');
                }
                return response.json();
            })
            .then(data => {
                console.log('포스트 생성 성공:', data);
                window.location.reload();
            })
            .catch(error => {
                console.log('포스트 생성 실패:', error);
            });
    } else {
        console.log('토큰이 없습니다. 로그인이 필요합니다.');
    }
}


// 서버에서 포스트 목록을 가져와서 페이지에 업데이트하는 함수
function updatePostList() {
    fetch('/api/posts')
        .then(response => {
            if (!response.ok) {
                throw new Error('목록을 가져오는데 실패했습니다.');
            }
            return response.json();
        })
        .then(data => {
            const postList = data.postList;
            postList.forEach(post => {
                addPostToPage(post.id, post.content);
            });
        })
        .catch(error => {
            console.log('Error:', error);
        });
}

// 포스트를 생성하여 페이지에 추가하는 함수
function addPostToPage(postId, postContent) {
    const newPostElement = document.createElement('div');
    newPostElement.classList.add('post');
    newPostElement.innerHTML = `
    <p class="post-content">${postContent}</p>
    <div class="likehate-buttons">
      <button class="like-button" onclick="handleLikeButtonClick(${postId}, this)">좋아요</button>
      <span class="like-cnt">0</span>
      <button class="hate-button" onclick="handleHateButtonClick(${postId}, this)">싫어요</button>
      <span class="hate-cnt">0</span>
    </div>
  `;
    const middleContainer = document.querySelector('.middle-container');
    middleContainer.appendChild(newPostElement);
}

// 페이지 로드 시 포스트 목록 업데이트
window.addEventListener('load', updatePostList);



// Function to update the like count display for a specific post
function updateLikeCnt(postId, likeCnt) {
    const likeCntElement = document.querySelector(`#post-${postId} .like-cnt`);
    likeCntElement.innerText = likeCnt;
}

// Function to update the dislike count display for a specific post
function updateHateCnt(postId, hateCnt) {
    const hateCntElement = document.querySelector(`#post-${postId} .hate-cnt`);
    hateCntElement.innerText = hateCnt;
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

// Attach event listeners to the like and hate buttons for each post
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
})
