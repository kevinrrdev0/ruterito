package gsg.corp.driver_domain.use_case

import android.net.Uri
import gsg.corp.driver_domain.repository.DriverRepository
import java.io.File

class UpdateRoute( private val repository: DriverRepository
) {
    suspend operator fun invoke(file: File, uri: Uri, path:String){
        return repository.updateRoute(file,uri,path)
    }
}