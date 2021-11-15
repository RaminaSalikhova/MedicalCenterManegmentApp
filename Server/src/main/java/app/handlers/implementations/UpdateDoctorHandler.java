package app.handlers.implementations;

import app.entity.*;
import app.enums.HANDLER_TYPE;
import app.handlers.RequestHandler;
import app.models.DataTransferModels.UpdateDoctorDto;
import app.services.*;
import org.apache.commons.lang3.time.DateUtils;

import java.sql.Time;
import java.util.Collection;
import java.util.Optional;

public class UpdateDoctorHandler extends RequestHandler<UpdateDoctorDto, String> {
    @Override
    public HANDLER_TYPE handlerType() {
        return HANDLER_TYPE.updateDoctor;
    }

    @Override
    protected String execute(UpdateDoctorDto updateDoctorDto) throws Exception {
        String response = "";

        DoctorService doctorService = new DoctorService();
        Optional<Doctor> doctor = Optional.ofNullable(doctorService.findById(updateDoctorDto.getDoctorID()));

        UserService userService = new UserService();
        User searchedUser = userService.findById(updateDoctorDto.getUserID());

        Doctor newDoctor = new Doctor();
        newDoctor.setId(updateDoctorDto.getDoctorID());
        newDoctor.setUserId(updateDoctorDto.getUserID());
        newDoctor.setUserByUserId(searchedUser);

        ScheduleService scheduleService = new ScheduleService();

        if (!updateDoctorDto.getFromWorkTime().equals("Не установлено")) {
            if (doctor.get().getScheduleId() != null) {
                Optional<Schedule> schedule = Optional.ofNullable(scheduleService.findById(doctor.get().getScheduleId()));
                if (schedule.isPresent()) {
                    schedule.get().setStartTime(Time.valueOf(updateDoctorDto.getFromWorkTime()));

                    Collection<Doctor> doctors=doctorService.findAll();
                    Collection<Doctor> doctorCollection=doctorService.findAll();
                    for(Doctor el: doctors){
                        Optional<Long> elid=Optional.ofNullable(el.getScheduleId());
                        Optional<Long> id=Optional.ofNullable(schedule.get().getId());
                        if(elid.isPresent()) {
                            if(id.isPresent()) {
                                if (elid.equals(id)) {
                                    doctorCollection.add(el);
                                }
                            }
                        }
                    }
                    schedule.get().setDoctorsById(doctorCollection);
                    scheduleService.update(schedule.get());

                    newDoctor.setScheduleId(schedule.get().getId());
                    newDoctor.setScheduleByScheduleId(schedule.get());
                }
            } else {
                Schedule newSchedule = new Schedule();
                newSchedule.setStartTime(Time.valueOf(updateDoctorDto.getFromWorkTime()));
                if(!updateDoctorDto.getTillWorkTime().equals(("Не установлено"))){
                    newSchedule.setEndTime(Time.valueOf(updateDoctorDto.getTillWorkTime()));
                }
                if(!updateDoctorDto.getRoomNumber().equals(("Не установлено"))){
                    newSchedule.setRoomNumber(updateDoctorDto.getRoomNumber());
                }
                Long count = Long.valueOf(scheduleService.findAll().size());
                count++;
                newSchedule.setId(count);

                scheduleService.save(newSchedule);
                newDoctor.setScheduleId(count);
                newDoctor.setScheduleByScheduleId(newSchedule);
            }
        }

        if (!updateDoctorDto.getTillWorkTime().equals("Не установлено")) {
            if (doctor.get().getScheduleId() != null) {
                Optional<Schedule> schedule = Optional.ofNullable(scheduleService.findById(doctor.get().getScheduleId()));
                if (schedule.isPresent()) {
                    schedule.get().setEndTime(Time.valueOf(updateDoctorDto.getTillWorkTime()));
                    Collection<Doctor> doctors=doctorService.findAll();
                    Collection<Doctor> doctorCollection=doctorService.findAll();
                    for(Doctor el: doctors){
                        Optional<Long> elid=Optional.ofNullable(el.getScheduleId());
                        Optional<Long> id=Optional.ofNullable(schedule.get().getId());
                        if(elid.isPresent()) {
                            if(id.isPresent()) {
                                if (elid.equals(id)) {
                                    doctorCollection.add(el);
                                }
                            }
                        }
                    }
                    schedule.get().setDoctorsById(doctorCollection);
                    scheduleService.update(schedule.get());

                    newDoctor.setScheduleId(schedule.get().getId());
                    newDoctor.setScheduleByScheduleId(schedule.get());
                }
            } else {
                Schedule newSchedule = new Schedule();
                newSchedule.setEndTime(Time.valueOf(updateDoctorDto.getTillWorkTime()));
                if(!updateDoctorDto.getFromWorkTime().equals(("Не установлено"))){
                    newSchedule.setStartTime(Time.valueOf(updateDoctorDto.getFromWorkTime()));
                }
                if(!updateDoctorDto.getRoomNumber().equals(("Не установлено"))){
                    newSchedule.setRoomNumber(updateDoctorDto.getRoomNumber());
                }
                Long count = Long.valueOf(scheduleService.findAll().size());
                count++;
                newSchedule.setId(count);

                scheduleService.save(newSchedule);
                newDoctor.setScheduleId(count);
                newDoctor.setScheduleByScheduleId(newSchedule);
            }
        }

        if (!updateDoctorDto.getRoomNumber().equals("Не установлено")) {
            if (doctor.get().getScheduleId() != null) {
                Optional<Schedule> schedule = Optional.ofNullable(scheduleService.findById(doctor.get().getScheduleId()));
                if (schedule.isPresent()) {
                    schedule.get().setRoomNumber(updateDoctorDto.getRoomNumber());
                    Collection<Doctor> doctors=doctorService.findAll();
                    Collection<Doctor> doctorCollection=doctorService.findAll();
                    for(Doctor el: doctors){
                        Optional<Long> elid=Optional.ofNullable(el.getScheduleId());
                        Optional<Long> id=Optional.ofNullable(schedule.get().getId());
                        if(elid.isPresent()) {
                            if(id.isPresent()) {
                                if (elid.equals(id)) {
                                    doctorCollection.add(el);
                                }
                            }
                        }
                    }
                    schedule.get().setDoctorsById(doctorCollection);
                    scheduleService.update(schedule.get());
                    newDoctor.setScheduleId(schedule.get().getId());
                    newDoctor.setScheduleByScheduleId(schedule.get());
                }
            } else {
                Schedule newSchedule = new Schedule();
                newSchedule.setRoomNumber(updateDoctorDto.getRoomNumber());
                if(!updateDoctorDto.getFromWorkTime().equals(("Не установлено"))){
                    newSchedule.setStartTime(Time.valueOf(updateDoctorDto.getFromWorkTime()));
                }
                if(!updateDoctorDto.getTillWorkTime().equals(("Не установлено"))){
                    newSchedule.setEndTime(Time.valueOf(updateDoctorDto.getTillWorkTime()));
                }
                Long count = Long.valueOf(scheduleService.findAll().size());
                count++;
                newSchedule.setId(count);

                scheduleService.save(newSchedule);
                newDoctor.setScheduleId(count);
            }
        }

        if (!updateDoctorDto.getSpecialization().equals("Не установлено")) {
            SpecializationService specializationService = new SpecializationService();

            Optional<Specialization> specialization = Optional.ofNullable(specializationService.findByName(updateDoctorDto.getSpecialization()));
            if (specialization.isPresent()) {
                newDoctor.setSpecializationBySpecializationId(specialization.get());
            } else {
                Specialization addSpecialization = new Specialization();
                addSpecialization.setName(updateDoctorDto.getSpecialization());
                specializationService.save(addSpecialization);

                Specialization searchAddSpec = specializationService.findByName(updateDoctorDto.getSpecialization());
                newDoctor.setSpecializationBySpecializationId(searchAddSpec);
            }

        }

        if (!updateDoctorDto.getDistrictName().

                equals("Не установлено")) {
            DistrictService districtService = new DistrictService();
            Optional<District> district =
                    Optional.ofNullable(districtService.findByName(updateDoctorDto.getDistrictName()));

            if (district.isPresent()) {
                newDoctor.setDistrictId(district.get().getId());
                newDoctor.setDistrictByDistrictId(district.get());
            } else {
                response = "Такого участка не сущетсвует";
            }
        }

        if (!updateDoctorDto.getSalary().

                equals("Не установлено")) {
            newDoctor.setSalary(Double.parseDouble(updateDoctorDto.getSalary()));
        }

        if (!updateDoctorDto.getExperience().

                equals("Не установлено")) {
            newDoctor.setExperience(Double.parseDouble(updateDoctorDto.getExperience()));
        }


        doctorService.update(newDoctor);
        return response;
    }
}
