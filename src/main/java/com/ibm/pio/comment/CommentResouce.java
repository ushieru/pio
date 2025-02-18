package com.ibm.pio.comment;

import java.util.List;
import java.util.UUID;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import io.quarkus.rest.data.panache.MethodProperties;
import io.quarkus.rest.data.panache.ResourceProperties;

@ResourceProperties(rolesAllowed = "user", path = "/api/v1/commets")
public interface CommentResouce extends PanacheEntityResource<Comment, UUID> {

        @MethodProperties(exposed = false)
        public List<Comment> list(Page page, Sort sort);

        @MethodProperties(exposed = false)
        public long count();

        @MethodProperties(exposed = false)
        public Comment get(UUID id);

        @MethodProperties(exposed = false)
        public Comment update(UUID id, Comment entity);
}
