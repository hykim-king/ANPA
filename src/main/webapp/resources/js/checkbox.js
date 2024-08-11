document.addEventListener('DOMContentLoaded', function() {  
    // 전체 체크박스 선택 클릭 이벤트
    checkAll.addEventListener('click', function() {
        const isChecked = checkAll.checked;
        const checkboxes = document.querySelectorAll('.chk');

        checkboxes.forEach(function(checkbox) {
            checkbox.checked = isChecked;
        });
    });
    // 개별 체크박스 클릭 시 처리
    document.addEventListener('change', function(event) {
        if (event.target.classList.contains('chk')) {
            const checkboxes = document.querySelectorAll('.chk');
            const allChecked = Array.from(checkboxes).every(function(checkbox) {
                return checkbox.checked;
            });

            checkAll.checked = allChecked;

            // 만약 모든 체크박스가 선택되지 않았다면, #checkAll 체크박스의 체크 상태를 해제합니다.
            if (!allChecked) {
                checkAll.checked = false;
            }
        }
    });
});