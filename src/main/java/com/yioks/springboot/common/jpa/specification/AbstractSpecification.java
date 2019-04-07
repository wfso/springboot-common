package com.yioks.springboot.common.jpa.specification;

import com.yioks.springboot.common.jpa.filter.IFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.Assert;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSpecification<T> implements Specification<T> {

  private static Logger logger = LoggerFactory.getLogger(AbstractSpecification.class);

  public static <T> Predicate generatePredicate(IFilter filter, Root<T> root, CriteriaBuilder builder) {
    if (filter.isAndComplex()) {
      return generatePredicate(filter.getList(), root, builder, true);
    }
    if (filter.isOrComplex()) {
      return generatePredicate(filter.getList(), root, builder, false);
    }
    Assert.state(false, "the filter.type only support [and] or [or]");
    return null;
  }

  public static <T> Predicate generatePredicate(List<? extends IFilter> filters, Root<T> root, CriteriaBuilder builder) {
    return generatePredicate(filters, root, builder, true);
  }

  public static <T> Predicate generatePredicate(List<? extends IFilter> filters, Root<T> root, CriteriaBuilder builder, boolean and) {
    List<Predicate> predicates = new ArrayList<>();
    for (IFilter filter : filters) {
      switch (filter.getType()) {
        case and: {
          predicates.add(generatePredicate(filter.getList(), root, builder, true));
          break;
        }
        case or: {
          predicates.add(generatePredicate(filter.getList(), root, builder, false));
          break;
        }
        case in: {
          if (filter.getClazz() == null) {
            predicates.add(root.get(filter.getKey()).in(filter.getInList()));
          } else {
            try {
              List<Object> os = generateInEnumObjects(filter);
              predicates.add(root.get(filter.getKey()).in(os));
            } catch (Exception e) {
              logger.warn(e.getMessage(), e);
              e.printStackTrace();
            }
          }
          break;
        }
        case notIn: {
          if (filter.getClazz() == null) {
            predicates.add(builder.not(root.get(filter.getKey()).in(filter.getInList())));
          } else {
            try {
              List<Object> os = generateInEnumObjects(filter);
              predicates.add(builder.not(root.get(filter.getKey()).in(os)));
            } catch (Exception e) {
              logger.warn(e.getMessage(), e);
              e.printStackTrace();
            }
          }
          break;
        }
        case equal: {
          if (filter.getClazz() == null) {
            predicates.add(builder.equal(root.get(filter.getKey()), filter.getValue()));
          } else {
            try {
              Object o = generateEqualEnumObject(filter);
              predicates.add(builder.equal(root.get(filter.getKey()), o));
            } catch (Exception e) {
              logger.warn(e.getMessage(), e);
              e.printStackTrace();
            }
          }
          break;
        }
        case greaterEqual: {
          predicates.add(builder.greaterThanOrEqualTo(root.get(filter.getKey()), filter.getValue()));
          break;
        }
        case lessEqual: {
          predicates.add(builder.lessThanOrEqualTo(root.get(filter.getKey()), filter.getValue()));
          break;
        }
        case greaterThen: {
          predicates.add(builder.greaterThan(root.get(filter.getKey()), filter.getValue()));
          break;
        }
        case lessThen: {
          predicates.add(builder.lessThan(root.get(filter.getKey()), filter.getValue()));
          break;
        }
        case notEqual: {
          if (filter.getClazz() == null) {
            predicates.add(builder.notEqual(root.get(filter.getKey()), filter.getValue()));
          } else {
            try {
              Object o = generateEqualEnumObject(filter);
              predicates.add(builder.notEqual(root.get(filter.getKey()), o));
            } catch (Exception e) {
              logger.warn(e.getMessage(), e);
              e.printStackTrace();
            }
          }
          break;
        }

        case eq: {
          predicates.add(builder.equal(root.get(filter.getKey()), Double.parseDouble(filter.getValue())));
          break;
        }
        case ge: {
          predicates.add(builder.ge(root.get(filter.getKey()), Double.parseDouble(filter.getValue())));
          break;
        }
        case le: {
          predicates.add(builder.le(root.get(filter.getKey()), Double.parseDouble(filter.getValue())));
          break;
        }
        case gt: {
          predicates.add(builder.gt(root.get(filter.getKey()), Double.parseDouble(filter.getValue())));
          break;
        }
        case lt: {
          predicates.add(builder.lt(root.get(filter.getKey()), Double.parseDouble(filter.getValue())));
          break;
        }
        case ne: {
          predicates.add(builder.notEqual(root.get(filter.getKey()), Double.parseDouble(filter.getValue())));
          break;
        }

        case like: {
          predicates.add(builder.like(root.get(filter.getKey()), filter.getValue()));
          break;
        }
        case notLike: {
          predicates.add(builder.notLike(root.get(filter.getKey()), filter.getValue()));
          break;
        }
        case isNull: {
          predicates.add(root.get(filter.getKey()).isNull());
          break;
        }
        case isNotNull: {
          predicates.add(root.get(filter.getKey()).isNotNull());
          break;
        }
        default: {
          logger.warn("the FilterType {} not found ", filter.getType().name());
        }
      }
    }
    if (and) {
      return builder.and(predicates.toArray(new Predicate[0]));
    } else {
      return builder.or(predicates.toArray(new Predicate[0]));
    }
  }

  private static List<Object> generateInEnumObjects(IFilter filter) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    Class clazz = filter.getClazz();
    Object[] objects = clazz.getEnumConstants();
    List<Object> os = new ArrayList<>();
    if (clazz.isEnum()) {
      Method method = clazz.getMethod("name");
      List<String> list = filter.getInList();
      for (Object o : objects) {
        String name = (String) method.invoke(o);
        if (list.contains(name)) {
          os.add(o);
        }
      }
    }
    return os;
  }

  private static Object generateEqualEnumObject(IFilter filter) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    Class clazz = filter.getClazz();
    if (clazz.isEnum()) {
      Object[] objects = clazz.getEnumConstants();
      Method method = clazz.getMethod("name");
      for (Object o : objects) {
        String name = (String) method.invoke(o);
        if (name.equals(filter.getValue())) {
          return o;
        }
      }
    }
    return null;
  }

}
