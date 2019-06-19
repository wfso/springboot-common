package com.yioks.springboot.common.base.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wfso
 * #-------------------------------------------------------------------------------------------------------------#
 * #               _____                    _____                    _____                   _______             #
 * #              /\    \                  /\    \                  /\    \                 /::\    \            #
 * #             /::\____\                /::\    \                /::\    \               /::::\    \           #
 * #            /:::/    /               /::::\    \              /::::\    \             /::::::\    \          #
 * #           /:::/   _/___            /::::::\    \            /::::::\    \           /::::::::\    \         #
 * #          /:::/   /\    \          /:::/\:::\    \          /:::/\:::\    \         /:::/~~\:::\    \        #
 * #         /:::/   /::\____\        /:::/__\:::\    \        /:::/__\:::\    \       /:::/    \:::\    \       #
 * #        /:::/   /:::/    /       /::::\   \:::\    \       \:::\   \:::\    \     /:::/    / \:::\    \      #
 * #       /:::/   /:::/   _/___    /::::::\   \:::\    \    ___\:::\   \:::\    \   /:::/____/   \:::\____\     #
 * #      /:::/___/:::/   /\    \  /:::/\:::\   \:::\    \  /\   \:::\   \:::\    \ |:::|    |     |:::|    |    #
 * #     |:::|   /:::/   /::\____\/:::/  \:::\   \:::\____\/::\   \:::\   \:::\____\|:::|____|     |:::|    |    #
 * #     |:::|__/:::/   /:::/    /\::/    \:::\   \::/    /\:::\   \:::\   \::/    / \:::\    \   /:::/    /     #
 * #      \:::\/:::/   /:::/    /  \/____/ \:::\   \/____/  \:::\   \:::\   \/____/   \:::\    \ /:::/    /      #
 * #       \::::::/   /:::/    /            \:::\    \       \:::\   \:::\    \        \:::\    /:::/    /       #
 * #        \::::/___/:::/    /              \:::\____\       \:::\   \:::\____\        \:::\__/:::/    /        #
 * #         \:::\__/:::/    /                \::/    /        \:::\  /:::/    /         \::::::::/    /         #
 * #          \::::::::/    /                  \/____/          \:::\/:::/    /           \::::::/    /          #
 * #           \::::::/    /                                     \::::::/    /             \::::/    /           #
 * #            \::::/    /                                       \::::/    /               \::/____/            #
 * #             \::/____/                                         \::/    /                 ~~                  #
 * #              ~~                                                \/____/                                      #
 * #-------------------------------------------------------------------------------------------------------------#
 * create by WFSO at 2019/6/19 9:29
 */
public interface CURDService<T,ID> {

  long countAll();

  List<T> getAll();

  Page<T> getPage(Pageable pageable);

  T getById(ID id);

  List<T> getByIds(Iterable<ID> ids);

  T create(T entity);

  T update(T entity);

  List<T> create(Iterable<T> entities);

  List<T> update(Iterable<T> entities);

  T updateIgnoreNull(T entity);

  T updateIgnoreEmpty(T entity);

  @Transactional
  default List<T> updateIgnoreNull(Iterable<T> entities) {
    List<T> list = new ArrayList<>();
    for (T entity : entities) {
      list.add(updateIgnoreNull(entity));
    }
    return list;
  }

  @Transactional
  default List<T> updateIgnoreEmpty(Iterable<T> entities) {
    List<T> list = new ArrayList<>();
    for (T entity : entities) {
      list.add(updateIgnoreEmpty(entity));
    }
    return list;
  }

  void remove(T entity);

  void remove(Iterable<T> entities);

  void removeById(ID id);

  @Transactional
  default void removeByIds(Iterable<ID> ids) {
    remove(getByIds(ids));
  }
}
