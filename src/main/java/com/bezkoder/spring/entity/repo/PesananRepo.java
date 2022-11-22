package com.bezkoder.spring.entity.repo;


import com.bezkoder.spring.entity.model.Pesanan;
import com.bezkoder.spring.login.models.User;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PesananRepo extends CrudRepository<Pesanan, Long>{
   Iterable<Pesanan> findByCustomer (User customer);
   Iterable<Pesanan> findByOwner (User owner);

   @Modifying
   @Query("update Pesanan p set p.bukti_pembayaran = :src where p.id = :id")
   void addBuktiPembayaran (@Param("src") String src, @Param("id") long id);

   @Modifying
   @Query("update Pesanan p set p.status_pembayaran = :status where p.id = :id")
   void updateStatusPembayaran (@Param("status") boolean status, @Param("id") long id);

   
}
