/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Repository.SupplierRepository;

/**
 *
 * @author Asus
 */
public class SupplierService<Supplier> {
    public void insertSupplier(Supplier sup){
        SupplierRepository supplierRepository= new SupplierRepository();
        supplierRepository.insert(sup);
        
    }
}
