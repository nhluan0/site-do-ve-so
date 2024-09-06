package com.luan.siteveso.jsonAjax;

import com.luan.siteveso.entity.KetQuaXoSo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class JsonKqxsDeletes {
    private Page<KetQuaXoSo> ketQuaXoSos;

    private Integer totalPage;

    private String mess;

    public JsonKqxsDeletes() {
    }

    public JsonKqxsDeletes(Page<KetQuaXoSo> ketQuaXoSos, Integer totalPage, String mess) {
        this.ketQuaXoSos = ketQuaXoSos;
        this.totalPage = totalPage;
        this.mess = mess;
    }

    public Page<KetQuaXoSo> getKetQuaXoSos() {
        return ketQuaXoSos;
    }

    public void setKetQuaXoSos(Page<KetQuaXoSo> ketQuaXoSos) {
        this.ketQuaXoSos = ketQuaXoSos;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    @Override
    public String toString() {
        return "JsonKqxsDeletes{" +
                "ketQuaXoSos=" + ketQuaXoSos +
                ", totalPage=" + totalPage +
                ", mess='" + mess + '\'' +
                '}';
    }
}
