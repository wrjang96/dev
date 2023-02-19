package com.example.fastcampusmysql.domain.member.repository;

import com.example.fastcampusmysql.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class MemberNicknameHistoryRepository {
    final private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    static final private String TABLE = "Mem";
    static final RowMapper<Member> ROW_MAPPER = (ResultSet resultSet, int rownum) -> Member
            .builder()
            .id(resultSet.getLong("id"))
            .email(resultSet.getString("email"))
            .nickname(resultSet.getString("nickname"))
            .birthday(resultSet.getObject("birthday", LocalDate.class))
            .createdAt(resultSet.getObject("createdAt", LocalDateTime.class))
            .build();
    public Optional<Member> findById(Long id){
        /* 간단한 설렉트 문
        * Select *
        * From Member
        * where id = : id
        *  */
        var sql = String.format("SELECT * FROM %s WHERE id = :id",TABLE);
        var params = new MapSqlParameterSource()
                .addValue("id", id);
        /*
        * BeanPropertyRowMapper를 이용하면 Mapping 로직을 없앨 수 있는데, Setter를 다 열어줘야 한다.
        * 이는 데이터를 어디서든 변경하게 만들 수 있기 떄문에 사용하지 않는 것을 권장한다.
        *  */
//        RowMapper<Member> rowMapper =(ResultSet resultSet, int rowNum) -> Member.builder()
//                .id(resultSet.getLong("id"))
//                .email(resultSet.getString("email"))
//                .nickname(resultSet.getString("nickname"))
//                .birthday(resultSet.getObject("birthday", LocalDate.class))
//                .createdAt(resultSet.getObject("createdAt", LocalDateTime.class))
//                .build();
//        var member = namedParameterJdbcTemplate.queryForObject(sql,param,rowMapper);
//        return Optional.ofNullable(member);
        List<Member> members = namedParameterJdbcTemplate.query(sql, params, ROW_MAPPER);

        // jdbcTemplate.query의 결과 사이즈가 0이면 null, 2 이상이면 예외
        Member nullableMember = DataAccessUtils.singleResult(members);
        return Optional.ofNullable(nullableMember);

    }

    public Member save(Member member){
        /*
        * member id 보고 갱신 삽입 정하고 반환값은 id를 ㄷㅁ아서 변환한다. */
//        return Member.builder().build();
        if (member.getId() == null){
            return insert(member);
        }
        return update(member);
    }

    private Member insert(Member member){
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(namedParameterJdbcTemplate.getJdbcTemplate())
                .withTableName("Member")
                .usingGeneratedKeyColumns("id");
        SqlParameterSource params = new BeanPropertySqlParameterSource(member);
        var id = simpleJdbcInsert.executeAndReturnKey(params).longValue();
        return Member
                .builder()
                .id(id)
                .email(member.getEmail())
                .nickname(member.getNickname())
                .birthday(member.getBirthday())
                .createdAt(member.getCreatedAt())
                .build();
    }

    private Member update(Member member){
        return member;
    }
}
