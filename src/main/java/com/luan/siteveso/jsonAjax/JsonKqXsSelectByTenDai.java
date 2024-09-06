package com.luan.siteveso.jsonAjax;

import com.luan.siteveso.entity.KetQuaXoSo;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class JsonKqXsSelectByTenDai{
    private List<KetQuaXoSo> ketQuaXoSoList;
    private int totalPage;

    public JsonKqXsSelectByTenDai() {
    }

    public JsonKqXsSelectByTenDai(List<KetQuaXoSo> ketQuaXoSoList, int totalPage) {
        this.ketQuaXoSoList = ketQuaXoSoList;
        this.totalPage = totalPage;
    }

    public List<KetQuaXoSo> getKetQuaXoSoList() {
        return ketQuaXoSoList;
    }

    public void setKetQuaXoSoList(List<KetQuaXoSo> ketQuaXoSoList) {
        this.ketQuaXoSoList = ketQuaXoSoList;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    @Override
    public String toString() {
        return "JsonKqXsSelectByTenDai{" +
                "ketQuaXoSoList=" + ketQuaXoSoList +
                ", totalPage=" + totalPage +
                '}';
    }
}
