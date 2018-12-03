package com.zhou.mjava.consult.chatreport;

/**
 * @author liqingzhou on 18/1/16
 */
public class Order {

    private String _id;
    private String _index;
    private OrderSource _source;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_index() {
        return _index;
    }

    public void set_index(String _index) {
        this._index = _index;
    }

    public OrderSource get_source() {
        return _source;
    }

    public void set_source(OrderSource _source) {
        this._source = _source;
    }
}
