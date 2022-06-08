package gsg.corp.driver_presentation.route_detail

sealed class RouteDetailEvent{
    data class OnStateSelected(val id: Int):RouteDetailEvent()
    data class OnCommentEnter(val comment: String):RouteDetailEvent()
    data class OnPathPhotoState(val path:String):RouteDetailEvent()
    data class OnPathPhotoCollect(val path:String):RouteDetailEvent()
}
