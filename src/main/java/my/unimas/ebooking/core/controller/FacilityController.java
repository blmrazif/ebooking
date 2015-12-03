package my.unimas.ebooking.core.controller;

import my.unimas.ebooking.core.helper.Constant;
import my.unimas.ebooking.core.model.Facility;
import my.unimas.ebooking.core.service.FacilityService;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by RazifBaital on 7/30/2015.
 */
@RestController
@RequestMapping(value="api/facility")
public class FacilityController {

    @Autowired
    private FacilityService facilityService;

//    @RequestMapping(value = "{id}", method = RequestMethod.GET)
//    public Facility getFacility(@PathVariable("id") Long id){
//        return facilityService.getFacility(id);
//    }

    @RequestMapping(value="{id}", method = RequestMethod.GET)
    public Facility getFacility(@PathVariable("id") Long id){
        return facilityService.getFacility(id);
    }
    @RequestMapping(value="{id}/check", method = RequestMethod.GET)
    public Map<String, Object> checkAvailability(@PathVariable("id") Long id,
                                     @RequestParam("startDate") String startDate,
                                     @RequestParam("endDate") String endDate){
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZ");
        DateTime sDate = formatter.parseDateTime(startDate);
        DateTime eDate = formatter.parseDateTime(endDate);
        Map<String, Object> data = new HashMap<>();
        data.put("available",facilityService.check(id, sDate, eDate));
        return data;
    }


    @RequestMapping(value={"","create"}, method = RequestMethod.POST)
    public Facility save(@RequestBody Facility facility){
        return facilityService.save(facility);
    }

    @RequestMapping(value="list", method=RequestMethod.GET)
    public List<Facility> findFacility(){
        return facilityService.findFacility();
    }

    @RequestMapping(value="user/list", method=RequestMethod.GET)
    public List<Facility> findAdminFacilityList(Principal principal){
        return facilityService.findByAdministrator(principal.getName());
    }

    @RequestMapping(value="list/{type}", method=RequestMethod.GET)
    public List<Facility> findByFacilityType(@PathVariable("type") String type){
        return facilityService.findByFacilityType(type);
    }


    @RequestMapping(value = "thumbnail/upload", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    Map<String, Object> uploadFile(@RequestParam("file") MultipartFile file,
                                   Principal principal,
                                   HttpServletRequest request) throws Exception {


        Map<String, Object> data = new HashMap<>();
        String originalFilename = file.getOriginalFilename();

        File dir = new File(Constant.UPLOAD_ROOT_DIR + "/thumbnail");
        dir.mkdirs();

        String filePath = "thumbnail" + "_" + originalFilename;
        File dest = new File(Constant.UPLOAD_ROOT_DIR + "/thumbnail/" + filePath);

        try {
            file.transferTo(dest);
            data.put("fileUrl", filePath);
            data.put("message", "success");
        } catch (IllegalStateException e) {
            data.put("message", "failed");
        }

        return data;
    }

    @RequestMapping(value="thumbnail/{id}")
    public @ResponseBody
    FileSystemResource getAttachmentFile(@PathVariable("id") Long id, HttpServletResponse response){
        Facility ra = facilityService.getFacility(id);
        response.setHeader("Content-Disposition", "attachment; filename="+ra.getThumbnail());
        return new FileSystemResource(Constant.UPLOAD_ROOT_DIR + "/thumbnail/" +ra.getThumbnail());
    }




}
