package org.zerock.task;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.zerock.domain.BoardAttachVO;
import org.zerock.mapper.BoardAttachMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Component
public class FileCheckTask {

	@Setter(onMethod_ = { @Autowired })
	private BoardAttachMapper attachMapper;

	private String getFolderYesterDay() { // localhost/뒤쪽의 경로를 지정하는 역할( 하루 전 날에 업로드 됬던 파일들이 위치한 폴더 ) 

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  //날짜형식

		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.DATE, -1);

		String str = sdf.format(cal.getTime());

		return str.replace("-", File.separator);  // 날짜형식에서 "-"를 해당 운영체제에서 지원하는 기호로 바꿔주는 역할(ex 2023-01-01 => 2023/01/01) 
	}
	
	//교재 p.600참고
	@Scheduled(cron = "* * 2 * * *")  // cron표현식 인터넷으로 알아보기(어렵지 않다 함 )  
	public void checkFiles() throws Exception { // 실제 삭제되야 되는 파일 체크

		log.warn("File Check Task run.................");
		log.warn(new Date());
		// file list in database
		List<BoardAttachVO> fileList = attachMapper.getOldFiles();  // 데이터 베이스에 저장 된 파일들의 등록 정보 가져옴

		// ready for check file in directory with database file list [데이터베이스 파일 목록이 있는 디렉터리(폴더)에서 파일 확인 준비 완료]
		List<Path> fileListPaths = fileList.stream()
				.map(vo -> Paths.get("C:\\upload", vo.getUploadPath(), vo.getUuid() + "_" + vo.getFileName()))
				.collect(Collectors.toList());

		// image file has thumnail file
		fileList.stream().filter(vo -> vo.isFileType() == true) //true면 이미지파일, faulse면 다른 파일
				.map(vo -> Paths.get("C:\\upload", vo.getUploadPath(), "s_" + vo.getUuid() + "_" + vo.getFileName()))
				.forEach(p -> fileListPaths.add(p));

		log.warn("===========================================");

		fileListPaths.forEach(p -> log.warn(p));

		// files in yesterday directory
		File targetDir = Paths.get("C:\\upload", getFolderYesterDay()).toFile(); // 해당 경로에 있는 것 중( 하루 전에 업로드 된 파일이 있는 폴더)

		File[] removeFiles = targetDir.listFiles(file -> fileListPaths.contains(file.toPath()) == false); // 불필요한 파일들 선별

		log.warn("-----------------------------------------");
		for (File file : removeFiles) { // 선별 된 불필요 파일 제거

			log.warn(file.getAbsolutePath());

			file.delete();

		}
	}
}