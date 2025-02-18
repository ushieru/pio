package com.ibm.pio.like;

import java.util.List;
import java.util.UUID;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import io.quarkus.rest.data.panache.MethodProperties;
import io.quarkus.rest.data.panache.ResourceProperties;

@ResourceProperties(rolesAllowed = "user", path = "/api/v1/likes")
public interface LikeResource extends PanacheEntityResource<Like, UUID> {
        @MethodProperties(exposed = false)
        public List<Like> list(Page page, Sort sort);

        @MethodProperties(exposed = false)
        public long count();

        @MethodProperties(exposed = false)
        public Like get(UUID id);

        @MethodProperties(exposed = false)
        public Like update(UUID id, Like entity);
}
