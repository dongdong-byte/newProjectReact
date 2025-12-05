
import { useState, useEffect } from 'react';
import './App.css'

// 1. 메뉴 구조 정의 (여기서 버튼 그룹을 관리합니다)
// 백엔드 DB 테이블 이름과 정확히 일치해야 데이터를 가져올 수 있습니다.
const MENU_GROUPS = {
  CAR: {
    label: '🚗 차량 관리',
    tables: [
      { name: 'car', label: '차량 실물(Car)' },
      { name: 'model', label: '차종/모델(Model)' },
      { name: 'brand', label: '제조사(Brand)' },
    ]
  },
  BRANCH: {
    label: '🏢 지점 관리',
    tables: [
      { name: 'branch', label: '지점(Branch)' },
      { name: 'pickup_zone', label: '픽업존(PickupZone)' },
    ]
  },
  PRICE: {
    label: '💰 가격/정책',
    tables: [
      { name: 'pricing_policy', label: '가격 정책(Policy)' },
      { name: 'pricing_policy_mapping', label: '정책 매핑(Mapping)' },
      { name: 'rental_price', label: '대여 요금(Price)' },
      { name: 'rental_price_applied_log', label: '요금 로그(Log)' },
    ]
  },
  RESERVATION: {
    label: '📅 예약 관리',
    tables: [
      { name: 'reservation', label: '예약(Reservation)' },
      { name: 'reservation_history', label: '예약 기록(History)' },
    ]
  },
  USERS: {
    label: '👤 회원/기타',
    tables: [
      { name: 'users', label: '회원(Users)' },
      // 필요한 다른 테이블이 있다면 여기에 추가하세요
    ]
  }
};

function App() {
  // --- 상태 관리 (State) ---
  const [activeCategory, setActiveCategory] = useState(null); // 현재 선택된 큰 카테고리 (예: CAR)
  const [activeTable, setActiveTable] = useState(null);       // 현재 선택된 상세 테이블 (예: model)
  const [tableData, setTableData] = useState([]);             // 받아온 데이터 저장
  const [loading, setLoading] = useState(false);              // 로딩 상태

  // --- 데이터 가져오기 (Effect) ---
  // activeTable이 바뀔 때마다 실행됩니다.
  useEffect(() => {
    if (!activeTable) return; // 선택된 테이블이 없으면 실행 안 함

    setLoading(true);
    // 백엔드 API 호출 (스크린샷의 주소 패턴에 맞췄습니다)
    // 예: http://localhost:8080/api/view?tableName=model
    fetch(`http://localhost:8080/api/view?tableName=${activeTable}`)
      .then(response => response.json())
      .then(data => {
        console.log(`데이터 도착 (${activeTable}):`, data);
        setTableData(data);
        setLoading(false);
      })
      .catch(error => {
        console.error('에러 발생:', error);
        setTableData([]);
        setLoading(false);
      });
  }, [activeTable]);

  // --- 이벤트 핸들러 ---
  
  // 1. 큰 카테고리 클릭 시
  const handleCategoryClick = (categoryKey) => {
    setActiveCategory(categoryKey);
    setActiveTable(null); // 카테고리를 바꾸면 상세 테이블 선택은 초기화
    setTableData([]);     // 데이터도 초기화
  };

  // 2. 상세 테이블 버튼 클릭 시
  const handleTableClick = (tableName) => {
    setActiveTable(tableName);
  };

  return (
    <div style={{ padding: '20px', fontFamily: 'sans-serif' }}>
      <h1>🚗 렌터카 통합 관리자 (Admin)</h1>

      {/* 1단계: 메인 카테고리 버튼 영역 */}
      <div style={{ marginBottom: '20px', borderBottom: '2px solid #ddd', paddingBottom: '10px' }}>
        {Object.keys(MENU_GROUPS).map((key) => (
          <button
            key={key}
            onClick={() => handleCategoryClick(key)}
            style={{
              padding: '10px 20px',
              marginRight: '10px',
              fontSize: '16px',
              backgroundColor: activeCategory === key ? '#007bff' : '#f0f0f0',
              color: activeCategory === key ? '#fff' : '#333',
              border: 'none',
              borderRadius: '5px',
              cursor: 'pointer'
            }}
          >
            {MENU_GROUPS[key].label}
          </button>
        ))}
      </div>

      {/* 2단계: 서브 메뉴 (테이블 선택) 영역 - 카테고리가 선택되었을 때만 보임 */}
      {activeCategory && (
        <div style={{ marginBottom: '20px' }}>
          <h3>📂 {MENU_GROUPS[activeCategory].label} 상세 선택</h3>
          {MENU_GROUPS[activeCategory].tables.map((table) => (
            <button
              key={table.name}
              onClick={() => handleTableClick(table.name)}
              style={{
                padding: '8px 15px',
                marginRight: '10px',
                marginBottom: '5px',
                backgroundColor: activeTable === table.name ? '#28a745' : '#e9ecef',
                color: activeTable === table.name ? '#fff' : '#333',
                border: '1px solid #ccc',
                borderRadius: '4px',
                cursor: 'pointer'
              }}
            >
              {table.label}
            </button>
          ))}
        </div>
      )}

      {/* 3단계: 데이터 테이블 영역 */}
      <div>
        {loading && <p>데이터를 불러오는 중입니다... ⏳</p>}
        
        {!loading && activeTable && tableData.length === 0 && (
          <p>데이터가 없습니다.</p>
        )}

        {!loading && tableData.length > 0 && (
          <div style={{ overflowX: 'auto' }}>
            <table style={{ width: '100%', borderCollapse: 'collapse', marginTop: '10px' }}>
              <thead>
                <tr style={{ backgroundColor: '#f8f9fa' }}>
                  {/* 데이터의 첫 번째 줄(키값)을 가져와서 헤더로 만듭니다 */}
                  {Object.keys(tableData[0]).map((head) => (
                    <th key={head} style={tableHeaderStyle}>{head}</th>
                  ))}
                </tr>
              </thead>
              <tbody>
                {tableData.map((row, index) => (
                  <tr key={index} style={{ borderBottom: '1px solid #eee' }}>
                    {Object.values(row).map((val, i) => (
                      <td key={i} style={tableCellStyle}>{String(val)}</td>
                    ))}
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </div>
  );
}

// 스타일 객체 (코드를 깔끔하게 하기 위해 분리)
const tableHeaderStyle = {
  border: '1px solid #ddd',
  padding: '12px',
  textAlign: 'left',
  fontWeight: 'bold',
  backgroundColor: '#007bff',
  color: 'white'
};

const tableCellStyle = {
  border: '1px solid #ddd',
  padding: '10px',
  fontSize: '14px'
};

export default App;