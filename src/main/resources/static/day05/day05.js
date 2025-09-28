// =======================================================================================
// [Day05] day05.js | rw 리팩토링
// - 엔드포인트: /day05/students (강사 코드와 1:1)
// - 변경점: 숫자 검증 강화, 성공 시 입력초기화, 에러 메시지 명확화
//          + 스피너 숨김 후 휠/방향키 값 변경 방지
// =======================================================================================
const BASE = "/day05/students";

// 공통: 테이블 렌더
function renderRows(list = []) {
    const tbody = document.getElementById("tbody");
    tbody.innerHTML = "";
    if (!list.length) {
        tbody.innerHTML = `<tr><td colspan="5">데이터가 없습니다.</td></tr>`;
        return;
    }
    list.forEach(r => {
        const tr = document.createElement("tr");
        tr.innerHTML = `
      <td>${r.sno}</td>
      <td>${r.name}</td>
      <td>${r.kor}</td>
      <td>${r.math}</td>
      <td><button onclick="del(${r.sno})">삭제</button></td>
    `;
        tbody.appendChild(tr);
    });
}

// 유틸: 안전한 숫자 읽기
function readNumber(id) {
    const el = document.getElementById(id);
    const n = el.valueAsNumber;           // '' -> NaN
    return Number.isFinite(n) ? n : null; // NaN 방지
}

// [추가] 숫자 input에서 마우스휠·방향키로 값 변경 방지
function lockNumberInputs() {
    document.querySelectorAll('input[type=number]').forEach(el => {
        el.addEventListener('wheel', e => e.preventDefault(), { passive: false });
        el.addEventListener('keydown', e => {
            if (e.key === 'ArrowUp' || e.key === 'ArrowDown') e.preventDefault();
        });
    });
}

// [1] 등록
async function save() {
    try {
        const name = document.getElementById("name").value.trim();
        const kor  = readNumber("kor");
        const math = readNumber("math");
        if (!name) return alert("이름을 입력하세요.");
        if (kor === null || math === null) return alert("국어/수학 점수는 숫자로 입력하세요.");

        const { data } = await axios.post(BASE, { name, kor, math }); // int
        if (data > 0) {
            alert("등록 성공");
            document.getElementById("name").value = "";
            document.getElementById("kor").value = "";
            document.getElementById("math").value = "";
        } else {
            alert("등록 실패");
        }
        await findAll();
    } catch (e) { console.error(e); alert("등록 중 오류가 발생했습니다."); }
}

// [2] 전체조회
async function findAll() {
    try {
        const { data } = await axios.get(BASE); // List<Map>
        renderRows(data || []);
    } catch (e) { console.error(e); alert("목록 조회 오류"); }
}

// [3] 수정 (강사님 update는 kor/math 모두 받으므로 둘 다 필수)
async function update() {
    try {
        const sno  = readNumber("u_sno");
        const kor  = readNumber("u_kor");
        const math = readNumber("u_math");
        if (sno === null) return alert("sno를 입력하세요.");
        if (kor === null || math === null) return alert("국어/수학 점수는 숫자로 입력하세요.");

        const { data } = await axios.put(BASE, { sno, kor, math }); // int
        alert(data > 0 ? "수정 성공" : "수정 실패");
        await findAll();
    } catch (e) { console.error(e); alert("수정 중 오류"); }
}

// [4] 삭제
async function del(sno) {
    try {
        if (!confirm(`#${sno} 삭제할까요?`)) return;
        const { data } = await axios.delete(`${BASE}?sno=${sno}`); // boolean
        alert(data ? "삭제 성공" : "삭제 실패");
        await findAll();
    } catch (e) { console.error(e); alert("삭제 중 오류"); }
}

// [5] 특정 점수 이상
async function findScores() {
    try {
        const mk = readNumber("minKor");
        const mm = readNumber("minMath");
        const minKor  = mk ?? 0;
        const minMath = mm ?? 0;
        const { data } = await axios.get(`${BASE}/find/scores`, { params: { minKor, minMath }});
        renderRows(data || []);
    } catch (e) { console.error(e); alert("점수 조회 오류"); }
}

// [6] 여러 명 등록
async function saveAll() {
    try {
        const raw = document.getElementById("batchSample").innerText.trim();
        // 유효 JSON만 허용
        let list;
        try { list = JSON.parse(raw); }
        catch { return alert("배치 JSON 형식이 올바르지 않습니다. 마지막 쉼표(,) 제거, 큰따옴표(\") 사용 확인."); }

        const { data } = await axios.post(`${BASE}/save/all`, list); // boolean
        alert(data ? "배치 등록 성공" : "배치 등록 실패");
        await findAll();
    } catch (e) { console.error(e); alert("배치 등록 중 오류"); }
}

// 초기 로딩
document.addEventListener("DOMContentLoaded", () => {
    lockNumberInputs();
    findAll();
});