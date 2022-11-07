package com.sans.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sans.model.entity.Client;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author Sans
* @description 针对表【client】的数据库操作Mapper
* @createDate 2022-10-08 11:53:23
* @Entity com.sans.model.entity.Client
*/
public interface ClientMapper extends BaseMapper<Client> {

    /**
     * 获取所有用户按名称顺序
     */
    @Select("select * from client where `delete`=0" +
            "\torder by ELT(INTERVAL(CONV(HEX(left(CONVERT(client_name USING gbk),1)),16,10),\n" +
            "\t0xB0A1,0xB0C5,0xB2C1,0xB4EE,0xB6EA,0xB7A2,0xB8C1,0xB9FE,\n" +
            "\t0xBBF7,0xBFA6,0xC0AC,0xC2E8,0xC4C3,0xC5B6,0xC5BE,0xC6DA,\n" +
            "\t0xC8BB,0xC8F6,0xCBFA,0xCDDA,0xCEF4,0xD1B9,0xD4D1),\n" +
            "\t'A','B','C','D','E','F','G','H','J','K','L','M','N','O','P',\n" +
            "\t'Q','R','S','T','W','X','Y','Z') asc;")
    List<Client> findAllOrderByName();
}




