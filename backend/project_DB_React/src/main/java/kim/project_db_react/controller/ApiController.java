package kim.project_db_react.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController // ⭐ 중요: HTML 말고 데이터(JSON)만 보낸다는 표시입니다.
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class ApiController {

    @Autowired
    private JdbcTemplate jdbcTemplate;
// 리액트가 요청할 주소: http://localhost:8080/api/view?tableName=car
    @GetMapping("/view")
    public List<Map<String, Object>> getTableData(@RequestParam String tableName)
    {
        // 보안상 허용된 테이블인지 검사하는게 좋지만, 일단 기능 구현부터!
        // 주의: 실제 서비스에선 이렇게 테이블명을 바로 넣으면 안됩니다 (SQL Injection 위험)
        // 하지만 지금 연습용 프로젝트에서는 가장 쉬운 방법입니다.
        String sql="select * from "+tableName;
        // 데이터를 리스트 형태로 가져와서 리액트에게 던져줍니다.
        return  jdbcTemplate.queryForList(sql);

    }


}
