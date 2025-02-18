package com.ibm.pio.follow;

import java.util.List;
import java.util.UUID;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import io.quarkus.rest.data.panache.MethodProperties;
import io.quarkus.rest.data.panache.ResourceProperties;

@ResourceProperties(rolesAllowed = "user", path = "/api/v1/follows")
public interface FollowResource extends PanacheEntityResource<Follow, UUID> {
        @MethodProperties(exposed = false)
        public List<Follow> list(Page page, Sort sort);

        @MethodProperties(exposed = false)
        public long count();

        @MethodProperties(exposed = false)
        public Follow get(UUID id);

        @MethodProperties(exposed = false)
        public Follow update(UUID id, Follow entity);
}
