package gsg.corp.driver_domain.use_case

import gsg.corp.driver_domain.model.Route
import gsg.corp.driver_domain.repository.DriverRepository

class GetRoutes (private val repository: DriverRepository){
    suspend operator fun invoke(id:Int):Result<List<Route>>{
        return repository.getRoutes(id)
    }
}