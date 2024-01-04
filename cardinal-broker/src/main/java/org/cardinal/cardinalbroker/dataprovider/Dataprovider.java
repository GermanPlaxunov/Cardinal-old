package org.cardinal.cardinalbroker.dataprovider;

import java.util.List;

public interface Dataprovider<T> {

    T getByName(String name);

    List<String> getAllNames();

}
