/*  클라이언트 스크립트 (task.js) | rw 25-09-24 생성
    - axios 로만 호출합니다. (가이드 준수)
    - API: /spring/task/board
*/

const API = "/spring/task/board";
const $ = (s)=>document.querySelector(s);

// =======================================================================================
// ✅ 1) 글쓰기
/*
    - rw) POST /spring/task/board
      R: { "title":"", "content":"" }
      W: true | false
*/
async function writePost(){
    const title = $("#title").value;
    const content = $("#content").value;
    const r = await axios.post(API, { title, content });
    if(r.data === true){ $("#title").value=""; $("#content").value=""; loadList(); }
    else alert("글쓰기 실패");
}

// =======================================================================================
// ✅ 2) 전체 글 조회
/*
    - rw) GET /spring/task/board
      R: -
      W: [ { "bno":0, "title":"", "content":"" }, ... ]
*/
async function loadList(){
    const r = await axios.get(API);
    const list = r.data || [];
    const html = list.length ? list.map(b=>`
    <tr>
      <td>${b.bno}</td>
      <td><a href="javascript:viewPost(${b.bno})">${b.title}</a></td>
      <td class="row-actions">
        <button onclick="viewPost(${b.bno})">보기</button>
        <button onclick="editPost(${b.bno})">수정</button>
        <button onclick="removePost(${b.bno})">삭제</button>
      </td>
    </tr>`).join("") : `<tr><td colspan="3">게시글이 없습니다.</td></tr>`;
    $("#listBody").innerHTML = html;
}

// =======================================================================================
// ✅ 3) 개별 글 조회
/*
    - rw) GET /spring/task/board/view
      R: ?bno=1
      W: { "bno":0, "title":"", "content":"" } | null
*/
async function viewPost(bno){
    const r = await axios.get(API + "/view", { params:{ bno }});
    const b = r.data;
    if(!b) return alert("존재하지 않는 글입니다.");
    $("#v-title").textContent = `${b.bno}. ${b.title}`;
    $("#v-content").textContent = b.content;
    $("#viewBox").style.display = "block";
    $("#btnEdit").onclick = ()=> editPost(b.bno);
    $("#btnClose").onclick = ()=> $("#viewBox").style.display = "none";
}

// =======================================================================================
// ✅ 4) 개별 글 수정
/*
    - rw) PUT /spring/task/board
      R: { "bno":0, "title":"", "content":"" }
      W: true | false
*/
async function editPost(bno){
    const cur = (await axios.get(API + "/view", { params:{ bno } })).data;
    if(!cur) return alert("글이 없습니다.");
    const title = prompt("새 제목", cur.title);   if(title===null) return;
    const content = prompt("새 내용", cur.content); if(content===null) return;
    const r = await axios.put(API, { bno, title, content });
    if(r.data === true){ loadList(); $("#viewBox").style.display="none"; }
    else alert("수정 실패");
}

// =======================================================================================
// ✅ 5) 개별 글 삭제
/*
    - rw) DELETE /spring/task/board
      R: ?bno=1
      W: true | false
*/
async function removePost(bno){
    if(!confirm("삭제할까요?")) return;
    const r = await axios.delete(API, { params:{ bno }});
    if(r.data === true){ loadList(); $("#viewBox").style.display="none"; }
    else alert("삭제 실패");
}

// 초기 로드
loadList();