package com.binarychris.carShow.service;

import com.binarychris.carShow.entity.Owner;

import java.util.List;

public interface OwnerService {
    List<Owner> getOwners();

    Owner getOwnerById(Long id);

    Owner addOwner(Owner owner);

    void deleteOwnerById(Long id);

    Owner updateOwner(Long id, Owner owner);
}
