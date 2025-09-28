// =======================================================================================
// ✅ [Day04/Frontend] day04.js
// fs) REST 엔드포인트 베이스 : /day04/student
// fe) axios 사용. 목록 → 등록/수정/삭제 후에는 항상 onFindAll()로 화면 갱신.
// =======================================================================================

console.log("day04.js open");

// 공통 상수 -------------------------------------------------------
const API = "/day04/student";

// [유틸] 입력 필드 가져오기 -----------------------------------------
const $name = () => document.querySelector(".name");
const $kor  = () => document.querySelector(".kor");
const $math = () => document.querySelector(".math");

// [유틸] 숫자 범위 간단 검증(0~100) -------------------------------
const isValidScore = (n) => Number.isInteger(n) && n >= 0 && n <= 100;

// =======================================================================================
// ✅ [4] 개별 삭제
// /*
//     - 매핑 방식: DELETE
//     - 요청 URL : /day04/student?sno=1
//     - 응답 데이터: 1(성공) | 0(실패)
// */
// =======================================================================================
const onDelete = async (sno) => {
    try {
        if (!confirm(`정말 삭제할까요? (sno=${sno})`)) return;
        const response = await axios.delete(`${API}?sno=${sno}`);
        if (response.data == 1) {
            onFindAll();
        } else {
            alert("삭제 실패");
        }
    } catch (e) {
        console.error(e);
        alert("삭제 중 오류가 발생했습니다.");
    }
};

// =======================================================================================
// ✅ [3] 개별 수정
// /*
//     - 매핑 방식: PUT
//     - 요청 URL : /day04/student
//     - 요청 데이터: { "sno":1, "name":"", "kor":0, "math":0 }
//     - 응답 데이터: 1 | 0
// */
// =======================================================================================
const onUpdate = async (sno) => {
    try {
        // (1) 수정 값 입력
        const name = prompt("새로운 이름을 입력하세요.");
        if (name === null) return; // 취소
        const kor = parseInt(prompt("새로운 국어점수(0~100)"), 10);
        if (!isValidScore(kor)) return alert("국어 점수는 0~100 사이의 정수여야 합니다.");
        const math = parseInt(prompt("새로운 수학점수(0~100)"), 10);
        if (!isValidScore(math)) return alert("수학 점수는 0~100 사이의 정수여야 합니다.");

        // (2) 객체화
        const obj = { sno, name: name.trim(), kor, math };

        // (3) axios 통신
        const response = await axios.put(API, obj);
        if (response.data == 1) {
            onFindAll();
        } else {
            alert("수정 실패");
        }
    } catch (e) {
        console.error(e);
        alert("수정 중 오류가 발생했습니다.");
    }
};

// =======================================================================================
// ✅ [2] 전체 조회
// /*
//     - 매핑 방식: GET
//     - 요청 URL : /day04/student
//     - 응답 데이터: [ {sno, name, kor, math}, ... ]
// */
// =======================================================================================
const onFindAll = async () => {
    try {
        const response = await axios.get(API);
        const list = response.data || [];

        const tbody = document.querySelector("tbody");
        let html = "";

        // [문서 렌더링]
        list.forEach((student) => {
            html += `
        <tr>
          <td>${student.name}</td>
          <td>${student.kor}</td>
          <td>${student.math}</td>
          <td class="row-actions">
            <button onclick="onUpdate(${student.sno})">수정</button>
            <button onclick="onDelete(${student.sno})">삭제</button>
          </td>
        </tr>`;
        });

        // 빈 목록 대응
        if (list.length === 0) {
            html = `<tr><td colspan="4" style="color:#777;">등록된 학생이 없습니다.</td></tr>`;
        }

        tbody.innerHTML = html;
    } catch (e) {
        console.error(e);
        alert("목록 조회 중 오류가 발생했습니다.");
    }
};

// 페이지 로드 시 자동 조회
onFindAll();

// =======================================================================================
// ✅ [1] 등록
// /*
//     - 매핑 방식: POST
//     - 요청 URL : /day04/student
//     - 요청 데이터: { "name":"", "kor":0, "math":0 }
//     - 응답 데이터: 1 | 0
// */
// =======================================================================================
const onSave = () => {
    // (1) 값 읽기 + 전처리
    const name = ($name().value || "").trim();
    const kor  = parseInt($kor().value, 10);
    const math = parseInt($math().value, 10);

    // (2) 간단 검증
    if (!name) return alert("학생명을 입력해주세요.");
    if (!isValidScore(kor))  return alert("국어 점수는 0~100 사이의 정수여야 합니다.");
    if (!isValidScore(math)) return alert("수학 점수는 0~100 사이의 정수여야 합니다.");

    const obj = { name, kor, math };
    console.log("[onSave] req:", obj);

    // (3) 비동기 통신 (then/catch 스타일 유지)
    axios
        .post(API, obj)
        .then((response) => {
            console.log("[onSave] res:", response.data);
            if (response.data == 1) {
                // 성공 시 입력 초기화 + 목록 갱신
                $name().value = "";
                $kor().value = "";
                $math().value = "";
                $name().focus();
                onFindAll();
            } else {
                alert("등록 실패");
            }
        })
        .catch((e) => {
            console.error(e);
            alert("등록 중 오류가 발생했습니다.");
        });
};