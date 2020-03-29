package com.progmasters.moovsmart.domain.search;

import com.progmasters.moovsmart.domain.PropertyAdvert;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PropertySpecificationBuilder {

    private final List<SearchCriteria> params;

    public PropertySpecificationBuilder() {
        params = new ArrayList<SearchCriteria>();
    }

    public PropertySpecificationBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<PropertyAdvert> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification> specs = params.stream()
                .map(PropertySpecification::new)
                .collect(Collectors.toList());

        Specification result = specs.get(0);

        for (int i = 1; i < params.size(); i++) {
            result = params.get(i)
                    .isOrPredicate()
                    ? Specification.where(result)
                    .or(specs.get(i))
                    : Specification.where(result)
                    .and(specs.get(i));
        }
        return result;
    }
}
