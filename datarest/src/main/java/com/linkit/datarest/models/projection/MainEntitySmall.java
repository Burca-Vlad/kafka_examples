package com.linkit.datarest.models.projection;

import com.linkit.datarest.models.MainEntity;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "small", types = MainEntity.class)
public interface MainEntitySmall {

    public Long getResourceId();

    public String getName();
}
