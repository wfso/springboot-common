package com.yioks.springboot.common.jpa.builder;

import java.util.List;

public interface OrFiltersBuilder extends FiltersBuilder {

  default OrFiltersBuilder orEqual(String key, String value) {
    equal(key, value);
    return this;
  }

  default OrFiltersBuilder orEqual(String key, String value, Class clazz) {
    equal(key, value, clazz);
    return this;
  }

  default OrFiltersBuilder orNotEqual(String key, String value) {
    notEqual(key, value);
    return this;
  }

  default OrFiltersBuilder orNotEqual(String key, String value, Class clazz) {
    notEqual(key, value, clazz);
    return this;
  }

  default OrFiltersBuilder orGreaterEqual(String key, String value) {
    greaterEqual(key, value);
    return this;
  }

  default OrFiltersBuilder orLessEqual(String key, String value) {
    lessEqual(key, value);
    return this;
  }

  default OrFiltersBuilder orGreaterThen(String key, String value) {
    greaterThen(key, value);
    return this;
  }

  default OrFiltersBuilder orLessThen(String key, String value) {
    lessThen(key, value);
    return this;
  }

  default OrFiltersBuilder orLike(String key, String value) {
    like(key, value);
    return this;
  }

  default OrFiltersBuilder orStartWith(String key, String value) {
    startWith(key, value);
    return this;
  }

  default OrFiltersBuilder orEndWith(String key, String value) {
    endWith(key, value);
    return this;
  }

  default OrFiltersBuilder orContain(String key, String value) {
    contain(key, value);
    return this;
  }

  default OrFiltersBuilder orNotLike(String key, String value) {
    notLike(key, value);
    return this;
  }

  default OrFiltersBuilder orNotStartWith(String key, String value) {
    notStartWith(key, value);
    return this;
  }

  default OrFiltersBuilder orNotEndWith(String key, String value) {
    notEndWith(key, value);
    return this;
  }

  default OrFiltersBuilder orNotContain(String key, String value) {
    notContain(key, value);
    return this;
  }

  default OrFiltersBuilder orIn(String key, List<String> strings) {
    in(key, strings);
    return this;
  }

  default OrFiltersBuilder orNotIn(String key, List<String> strings) {
    notIn(key, strings);
    return this;
  }

  default OrFiltersBuilder orIn(String key, List<String> strings, Class clazz) {
    in(key, strings, clazz);
    return this;
  }

  default OrFiltersBuilder orNotIn(String key, List<String> strings, Class clazz) {
    notIn(key, strings, clazz);
    return this;
  }

  default OrFiltersBuilder orIsNull(String key) {
    isNull(key);
    return this;
  }

  default OrFiltersBuilder orIsNotNull(String key) {
    isNotNull(key);
    return this;
  }

  default OrFiltersBuilder orIsEmpty(String key) {
    isEmpty(key);
    return this;
  }

  default OrFiltersBuilder orIsNotEmpty(String key) {
    isNotEmpty(key);
    return this;
  }

  default OrFiltersBuilder orEQ(String key, String value) {
    eq(key, value);
    return this;
  }

  default OrFiltersBuilder orNE(String key, String value) {
    ne(key, value);
    return this;
  }

  default OrFiltersBuilder orGE(String key, String value) {
    ge(key, value);
    return this;
  }

  default OrFiltersBuilder orLE(String key, String value) {
    le(key, value);
    return this;
  }

  default OrFiltersBuilder orGT(String key, String value) {
    gt(key, value);
    return this;
  }

  default OrFiltersBuilder orLT(String key, String value) {
    lt(key, value);
    return this;
  }

  AndConditionBuilder createAndCondition();

  interface AndConditionBuilder extends ConditionBuilder {

    default AndConditionBuilder andEqual(String key, String value) {
      equal(key, value);
      return this;
    }

    default AndConditionBuilder andEqual(String key, String value, Class clazz) {
      equal(key, value, clazz);
      return this;
    }

    default AndConditionBuilder andNotEqual(String key, String value) {
      notEqual(key, value);
      return this;
    }

    default AndConditionBuilder andNotEqual(String key, String value, Class clazz) {
      notEqual(key, value, clazz);
      return this;
    }

    default AndConditionBuilder andGreaterEqual(String key, String value) {
      greaterEqual(key, value);
      return this;
    }

    default AndConditionBuilder andLessEqual(String key, String value) {
      lessEqual(key, value);
      return this;
    }

    default AndConditionBuilder andGreaterThen(String key, String value) {
      greaterThen(key, value);
      return this;
    }

    default AndConditionBuilder andLessThen(String key, String value) {
      lessThen(key, value);
      return this;
    }

    default AndConditionBuilder andLike(String key, String value) {
      like(key, value);
      return this;
    }

    default AndConditionBuilder andStartWith(String key, String value) {
      startWith(key, value);
      return this;
    }

    default AndConditionBuilder andEndWith(String key, String value) {
      endWith(key, value);
      return this;
    }

    default AndConditionBuilder andContain(String key, String value) {
      contain(key, value);
      return this;
    }


    default AndConditionBuilder andNotLike(String key, String value) {
      notLike(key, value);
      return this;
    }

    default AndConditionBuilder andNotStartWith(String key, String value) {
      notStartWith(key, value);
      return this;
    }

    default AndConditionBuilder andNotEndWith(String key, String value) {
      notEndWith(key, value);
      return this;
    }

    default AndConditionBuilder andNotContain(String key, String value) {
      notContain(key, value);
      return this;
    }

    default AndConditionBuilder andIn(String key, List<String> strings) {
      in(key, strings);
      return this;
    }

    default AndConditionBuilder andNotIn(String key, List<String> strings) {
      notIn(key, strings);
      return this;
    }

    default AndConditionBuilder andIn(String key, List<String> strings, Class clazz) {
      in(key, strings, clazz);
      return this;
    }

    default AndConditionBuilder andNotIn(String key, List<String> strings, Class clazz) {
      notIn(key, strings, clazz);
      return this;
    }

    default AndConditionBuilder andIsNull(String key) {
      isNull(key);
      return this;
    }

    default AndConditionBuilder andIsNotNull(String key) {
      isNotNull(key);
      return this;
    }

    default AndConditionBuilder andIsEmpty(String key) {
      isEmpty(key);
      return this;
    }

    default AndConditionBuilder andIsNotEmpty(String key) {
      isNotEmpty(key);
      return this;
    }

    default AndConditionBuilder andEQ(String key, String value) {
      eq(key, value);
      return this;
    }

    default AndConditionBuilder andNE(String key, String value) {
      ne(key, value);
      return this;
    }

    default AndConditionBuilder andGE(String key, String value) {
      ge(key, value);
      return this;
    }

    default AndConditionBuilder andLE(String key, String value) {
      le(key, value);
      return this;
    }

    default AndConditionBuilder andGT(String key, String value) {
      gt(key, value);
      return this;
    }

    default AndConditionBuilder andLT(String key, String value) {
      lt(key, value);
      return this;
    }

    OrFiltersBuilder endAnd();
  }
}
