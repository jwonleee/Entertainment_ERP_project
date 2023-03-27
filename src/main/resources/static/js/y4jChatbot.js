const chatBox = document.querySelector('.chat-box');
let userMessages = [];
let assistantMessages = [];

function spinner() {
  document.getElementById('loader').style.display = "block";
}

const sendMessage = async () => {
  const chatInput = document.querySelector('.chat-input input');
  const chatMessage = document.createElement('div');
  chatMessage.classList.add('chat-message');
  chatMessage.innerHTML = `<p class='user'>${chatInput.value}</p>`;
  chatBox.appendChild(chatMessage);

  //userMessage 메세지 추가
  userMessages.push(chatInput.value);

  chatInput.value = '';

  //API Gateway 엔드포인트
  const response = await fetch('https://ss7yz120b0.execute-api.ap-northeast-2.amazonaws.com/y4j/y4jChatbot', {
//  const response = await fetch('http://localhost:3000/y4jChatbot', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
      userMessages: userMessages,
      assistantMessages: assistantMessages,
    })
  });

  const data = await response.json();
  document.getElementById('loader').style.display = "none";

  //assistantMessage 메세지 추가
  assistantMessages.push(data.assistant);

  const astrologerMessage = document.createElement('div');
  astrologerMessage.classList.add('chat-message');
  astrologerMessage.innerHTML = `<p class='assistant'>${data.assistant}</p>`;
  chatBox.appendChild(astrologerMessage);
};
document.querySelector('.chat-input button').addEventListener('click', sendMessage);

