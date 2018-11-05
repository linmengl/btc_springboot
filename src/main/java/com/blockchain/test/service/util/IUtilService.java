package com.blockchain.test.service.util;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface IUtilService {

    Long generateId();

    boolean isChinesesName(String name);

    String longList2Str(List<Long> list2beConverted, String seperator);

    String longList2Str(List<Long> list2beConverted);

    List<Long> str2LongList(String str2beConverted);

    List<Long> str2LongList(String str2beConverted, String seperator);

    String number2CNMoney (BigDecimal numberOfMoney);

    String safeMap2Str(Map<String, String> info);

    String safeObjectMap2Str(Map<String, Object> info);

    String generateKey(int round, int length);
}
