package com.jiangnan.dubbo.merger;

import com.alibaba.dubbo.rpc.cluster.Merger;

/**
 * @author 吴叶俊
 */
public class StringMerger implements Merger<String> {
    @Override
    public String merge(String... items) {
        StringBuilder sb = new StringBuilder();
        if (null != items && items.length > 0) {
            for (String item : items) {
                sb.append(item).append("&");
            }
            sb.deleteCharAt(sb.lastIndexOf("&"));
        }
        return sb.toString();
    }
}
