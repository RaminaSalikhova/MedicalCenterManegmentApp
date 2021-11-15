package app.handlers.implementations;

import app.entity.Doctor;
import app.enums.HANDLER_TYPE;
import app.handlers.RequestHandler;
import app.models.DataTransferModels.GetReportDto;
import app.services.DistrictService;
import app.services.DoctorService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GetReportHandler extends RequestHandler<GetReportDto, String> {
    @Override
    public HANDLER_TYPE handlerType() {
        return HANDLER_TYPE.getReport;
    }

    @Override
    protected String execute(GetReportDto getReportDto) throws Exception {
        DistrictService districtService=new DistrictService();

        String content = "Отчет по загруженности участков\n"
                + "Количество участков: " +districtService.findAll().size() + ";\n";


        DoctorService doctorService=new DoctorService();
        List<Doctor> doctorCollection=doctorService.findAll();
        Collections.addAll(doctorCollection);
//        Stream<Doctor> doctorStream = null;
//        doctorStream=Stream.concat(doctorStream.collect(doctorCollection));
//        for(Doctor el: doctorCollection){
//            doctorStream=Stream.of();
//        }
//        Stream<List<Doctor>> doctorCollection=Stream.of(doctorService.findAll());

        Map<Long, List<Doctor>> doctorsCountByDistricts = doctorCollection.stream().collect(
                Collectors.groupingBy(Doctor::getDistrictId));

        for(Map.Entry<Long, List<Doctor>> item : doctorsCountByDistricts.entrySet()){

            System.out.println(item.getKey());
            System.out.println(item.getValue().size());
            content=content.concat("Участок: "+item.getKey()+" Кол-во врачей: " +item.getValue().size()+"\n");
        }
//        int amountOfDoctorsPerDistrict = 0;
//        List<GetDoctorListForAdminAtm> list = getDoctorsToAdd();
//        for (GetDoctorListForAdminAtm el : list) {
//            if ()
//                amountOfDoctorsPerDistrict++;
//            content = content.concat("Участок: " + el.getDistrictName());
//        }
//        content = content.concat("Участок: ");

        return content;
    }
}
