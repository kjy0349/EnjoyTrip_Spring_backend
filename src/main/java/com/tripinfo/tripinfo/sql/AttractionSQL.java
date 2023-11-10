package com.tripinfo.tripinfo.sql;

public class AttractionSQL {
    public String searchByTitle(String title, int sidoCode, int contentTypeId) {
        StringBuilder query = new StringBuilder();
        query.append("select * from attraction_info");
        if (!title.isEmpty()) {
            query.append(" where title like '%").append(title).append("%'");
            if (sidoCode != 0) query.append(" and sido_code=").append(sidoCode);
            if (contentTypeId != 0) query.append(" and content_type_id=").append(contentTypeId);
        } else {
            if (sidoCode != 0 || contentTypeId != 0) {
                query.append(" where ");
                if (sidoCode != 0) query.append(" and sido_code=").append(sidoCode);
                if (contentTypeId != 0) query.append(" and content_type_id=").append(contentTypeId);
            }
        }
        return query.toString();
    }
}
