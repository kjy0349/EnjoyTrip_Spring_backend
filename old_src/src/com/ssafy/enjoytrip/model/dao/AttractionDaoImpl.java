package com.ssafy.enjoytrip.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import com.ssafy.enjoytrip.model.AttractionInfoDto;
import com.ssafy.util.DBUtil;

public class AttractionDaoImpl implements AttractionDao{
	// 싱글톤 작업
	private static AttractionDaoImpl AttractionDaoImpl = new AttractionDaoImpl();
	private AttractionDaoImpl() {}
	public static AttractionDaoImpl getAttractionDao() {
		return AttractionDaoImpl;
	}

	
	@Override
	public List<AttractionInfoDto> randomAttList(Connection con) {
		List<AttractionInfoDto> list = new ArrayList<>();
StringBuilder sql = new StringBuilder();
		
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			con = DBUtil.getInstance().getConnection();
			
			sql.append("select * from attraction_info order by rand() limit 6;");

			stmt = con.prepareStatement(sql.toString());
			

			rset = stmt.executeQuery();
			
			while(rset.next()) {
				AttractionInfoDto AIDto = new AttractionInfoDto();
				AIDto.setContentId(rset.getInt("Content_id"));
				AIDto.setContentTypeId(rset.getInt("content_type_id"));
				AIDto.setTitle(rset.getString("title"));
				AIDto.setAddr1(rset.getString("addr1"));
				AIDto.setAddr2(rset.getString("addr2"));
				AIDto.setTel(rset.getString("tel"));
				AIDto.setFirstImage(rset.getString("first_image"));
				AIDto.setFirstImage2(rset.getString("first_image2"));
				AIDto.setReadcount(rset.getInt("readcount"));
				AIDto.setSidoCode(rset.getInt("sido_code"));
				AIDto.setGugunCode(rset.getInt("gugun_code"));
				AIDto.setLatitude(rset.getDouble("latitude"));
				AIDto.setLongitude(rset.getDouble("longitude"));
				AIDto.setMlevel(rset.getString("mlevel"));
				list.add(AIDto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.getInstance().close(rset, stmt);
		}
		return list;
	}
	
	@Override
	public List<AttractionInfoDto> attractionList(Connection con, AttractionInfoDto attractionInfoDto) {
		// TODO Auto-generated method stub
		List<AttractionInfoDto> list = new ArrayList<>();
		
		StringBuilder sql = new StringBuilder();
		
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			con = DBUtil.getInstance().getConnection();
			
			sql.append("select * from attraction_info");
			
			HashMap<String, Object> params = new HashMap<>();
			if (attractionInfoDto.getContentId() != 0) params.put("content_type_id", attractionInfoDto.getContentId());
			if (attractionInfoDto.getSidoCode() != 0) params.put("sido_code", attractionInfoDto.getSidoCode());
			if (params.size() != 0) sql.append(" where ");
			int size = params.size();
			int cursor = 1;
			
			for(String key : params.keySet()) {
				sql.append(key + " = ?");
				if (--size != 0) sql.append(" and ");
			}
			stmt = con.prepareStatement(sql.toString());
			
			for (Object value : params.values()) {
				if (value instanceof String) {
					stmt.setString(cursor++, value.toString());
				}
				if (value instanceof Integer) {
					stmt.setInt(cursor++, Integer.parseInt(value.toString()));
				}
			} 
			rset = stmt.executeQuery();
			
			while(rset.next()) {
				AttractionInfoDto AIDto = new AttractionInfoDto();
				AIDto.setContentId(rset.getInt("Content_id"));
				AIDto.setContentTypeId(rset.getInt("content_type_id"));
				AIDto.setTitle(rset.getString("title"));
				AIDto.setAddr1(rset.getString("addr1"));
				AIDto.setAddr2(rset.getString("addr2"));
				AIDto.setTel(rset.getString("tel"));
				AIDto.setFirstImage(rset.getString("first_image"));
				AIDto.setFirstImage2(rset.getString("first_image2"));
				AIDto.setReadcount(rset.getInt("readcount"));
				AIDto.setSidoCode(rset.getInt("sido_code"));
				AIDto.setGugunCode(rset.getInt("gugun_code"));
				AIDto.setLatitude(rset.getDouble("latitude"));
				AIDto.setLongitude(rset.getDouble("longitude"));
				AIDto.setMlevel(rset.getString("mlevel"));
				list.add(AIDto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.getInstance().close(rset, stmt);
		}
		return list;
	}

	@Override
	public List<AttractionInfoDto> searchByTitle(Connection con, String title, int sidoCode) {
		List<AttractionInfoDto> list = new ArrayList<>();

		StringBuilder sql = new StringBuilder();
		
		PreparedStatement stmt = null;
		ResultSet rset = null;

		try {
			con = DBUtil.getInstance().getConnection();
			
			sql.append("select * from attraction_info");
			
			HashMap<String, Object> params = new LinkedHashMap<>();
			
			if (sidoCode != 0) params.put("sido_code", sidoCode);
			
			sql.append(" where ");
			
			int size = params.size();
			int cursor = 1;
			
			for(String key : params.keySet()) {

				sql.append(key + " = ?");
				if (size-- != 0) sql.append(" and ");
			}
			
			sql.append(" title like ?");
			System.out.println(sql.toString());
			stmt = con.prepareStatement(sql.toString());
			
			for (Object value : params.values()) {
				if (value instanceof Integer) stmt.setInt(cursor++, Integer.parseInt(value.toString()));
			} 


			stmt.setString(cursor, "%" + title + "%");
			
			rset = stmt.executeQuery();

			while(rset.next()) {
				AttractionInfoDto AIDto = new AttractionInfoDto();

				AIDto.setContentId(rset.getInt("Content_id"));
				AIDto.setContentTypeId(rset.getInt("content_type_id"));
				AIDto.setTitle(rset.getString("title"));
				AIDto.setAddr1(rset.getString("addr1"));
				AIDto.setAddr2(rset.getString("addr2"));
				AIDto.setTel(rset.getString("tel"));
				AIDto.setFirstImage(rset.getString("first_image"));
				AIDto.setFirstImage2(rset.getString("first_image2"));
				AIDto.setReadcount(rset.getInt("readcount"));
				AIDto.setSidoCode(rset.getInt("sido_code"));
				AIDto.setGugunCode(rset.getInt("gugun_code"));
				AIDto.setLatitude(rset.getDouble("latitude"));
				AIDto.setLongitude(rset.getDouble("longitude"));
				AIDto.setMlevel(rset.getString("mlevel"));

				list.add(AIDto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.getInstance().close(rset, stmt);
		}
		return list;
	}

}
