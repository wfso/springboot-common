package com.yioks.springboot.common.jpa.builder;

import java.util.List;

public interface AndFiltersBuilder extends FiltersBuilder {

  default AndFiltersBuilder andEqual(String key, String value) {
    equal(key, value);
    return this;
  }

  default AndFiltersBuilder andEqual(String key, String value, Class clazz) {
    equal(key, value, clazz);
    return this;
  }

  default AndFiltersBuilder andNotEqual(String key, String value) {
    notEqual(key, value);
    return this;
  }

  default AndFiltersBuilder andNotEqual(String key, String value, Class clazz) {
    notEqual(key, value, clazz);
    return this;
  }

  default AndFiltersBuilder andGreaterEqual(String key, String value) {
    greaterEqual(key, value);
    return this;
  }

  default AndFiltersBuilder andLessEqual(String key, String value) {
    lessEqual(key, value);
    return this;
  }

  default AndFiltersBuilder andGreaterThen(String key, String value) {
    greaterThen(key, value);
    return this;
  }

  default AndFiltersBuilder andLessThen(String key, String value) {
    lessThen(key, value);
    return this;
  }

  default AndFiltersBuilder andLike(String key, String value) {
    like(key, value);
    return this;
  }


  default AndFiltersBuilder andStartWith(String key, String value) {
    startWith(key, value);
    return this;
  }

  default AndFiltersBuilder andEndWith(String key, String value) {
    endWith(key, value);
    return this;
  }

  default AndFiltersBuilder andContain(String key, String value) {
    contain(key, value);
    return this;
  }

  default AndFiltersBuilder andNotLike(String key, String value) {
    notLike(key, value);
    return this;
  }

  default AndFiltersBuilder andNotStartWith(String key, String value) {
    notStartWith(key, value);
    return this;
  }

  default AndFiltersBuilder andNotEndWith(String key, String value) {
    notEndWith(key, value);
    return this;
  }

  default AndFiltersBuilder andNotContain(String key, String value) {
    notContain(key, value);
    return this;
  }

  default AndFiltersBuilder andIn(String key, List<String> strings) {
    in(key, strings);
    return this;
  }

  default AndFiltersBuilder andNotIn(String key, List<String> strings) {
    notIn(key, strings);
    return this;
  }

  default AndFiltersBuilder andIn(String key, List<String> strings, Class clazz) {
    in(key, strings, clazz);
    return this;
  }

  default AndFiltersBuilder andNotIn(String key, List<String> strings, Class clazz) {
    notIn(key, strings, clazz);
    return this;
  }

  default AndFiltersBuilder andIsNull(String key) {
    isNull(key);
    return this;
  }

  default AndFiltersBuilder andIsNotNull(String key) {
    isNotNull(key);
    return this;
  }

  default AndFiltersBuilder andIsEmpty(String key) {
    isEmpty(key);
    return this;
  }

  default AndFiltersBuilder andIsNotEmpty(String key) {
    isNotEmpty(key);
    return this;
  }

  default AndFiltersBuilder andEQ(String key, String value) {
    eq(key, value);
    return this;
  }

  default AndFiltersBuilder andNE(String key, String value) {
    ne(key, value);
    return this;
  }

  default AndFiltersBuilder andGE(String key, String value) {
    ge(key, value);
    return this;
  }

  default AndFiltersBuilder andLE(String key, String value) {
    le(key, value);
    return this;
  }

  default AndFiltersBuilder andGT(String key, String value) {
    gt(key, value);
    return this;
  }

  default AndFiltersBuilder andLT(String key, String value) {
    lt(key, value);
    return this;
  }


  OrConditionBuilder createOrCondition();

  interface OrConditionBuilder extends ConditionBuilder {

    default OrConditionBuilder orEqual(String key, String value) {
      equal(key, value);
      return this;
    }

    default OrConditionBuilder orEqual(String key, String value, Class clazz) {
      equal(key, value, clazz);
      return this;
    }

    default OrConditionBuilder orNotEqual(String key, String value) {
      notEqual(key, value);
      return this;
    }

    default OrConditionBuilder orNotEqual(String key, String value, Class clazz) {
      notEqual(key, value, clazz);
      return this;
    }

    default OrConditionBuilder orGreaterEqual(String key, String value) {
      greaterEqual(key, value);
      return this;
    }

    default OrConditionBuilder orLessEqual(String key, String value) {
      lessEqual(key, value);
      return this;
    }

    default OrConditionBuilder orGreaterThen(String key, String value) {
      greaterThen(key, value);
      return this;
    }

    default OrConditionBuilder orLessThen(String key, String value) {
      lessThen(key, value);
      return this;
    }

    default OrConditionBuilder orLike(String key, String value) {
      like(key, value);
      return this;
    }

    default OrConditionBuilder orStartWith(String key, String value) {
      startWith(key, value);
      return this;
    }

    default OrConditionBuilder orEndWith(String key, String value) {
      endWith(key, value);
      return this;
    }

    default OrConditionBuilder orContain(String key, String value) {
      contain(key, value);
      return this;
    }

    default OrConditionBuilder orNotLike(String key, String value) {
      notLike(key, value);
      return this;
    }

    default OrConditionBuilder orNotStartWith(String key, String value) {
      notStartWith(key, value);
      return this;
    }

    default OrConditionBuilder orNotEndWith(String key, String value) {
      notEndWith(key, value);
      return this;
    }

    default OrConditionBuilder orNotContain(String key, String value) {
      notContain(key, value);
      return this;
    }

    default OrConditionBuilder orIn(String key, List<String> strings) {
      in(key, strings);
      return this;
    }

    default OrConditionBuilder orNotIn(String key, List<String> strings) {
      notIn(key, strings);
      return this;
    }

    default OrConditionBuilder orIn(String key, List<String> strings, Class clazz) {
      in(key, strings, clazz);
      return this;
    }

    default OrConditionBuilder orNotIn(String key, List<String> strings, Class clazz) {
      notIn(key, strings, clazz);
      return this;
    }

    default OrConditionBuilder orIsNull(String key) {
      isNull(key);
      return this;
    }

    default OrConditionBuilder orIsNotNull(String key) {
      isNotNull(key);
      return this;
    }

    default OrConditionBuilder orIsEmpty(String key) {
      isEmpty(key);
      return this;
    }

    default OrConditionBuilder orIsNotEmpty(String key) {
      isNotEmpty(key);
      return this;
    }

    default OrConditionBuilder orEQ(String key, String value) {
      eq(key, value);
      return this;
    }

    default OrConditionBuilder orNE(String key, String value) {
      ne(key, value);
      return this;
    }

    default OrConditionBuilder orGE(String key, String value) {
      ge(key, value);
      return this;
    }

    default OrConditionBuilder orLE(String key, String value) {
      le(key, value);
      return this;
    }

    default OrConditionBuilder orGT(String key, String value) {
      gt(key, value);
      return this;
    }

    default OrConditionBuilder orLT(String key, String value) {
      lt(key, value);
      return this;
    }

    AndFiltersBuilder endOr();
  }
}
