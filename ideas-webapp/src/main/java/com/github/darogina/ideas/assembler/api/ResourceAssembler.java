package com.github.darogina.ideas.assembler.api;

import org.springframework.hateoas.ResourceSupport;

public interface ResourceAssembler<T, D extends ResourceSupport> extends org.springframework.hateoas.ResourceAssembler<T, D> {
}
