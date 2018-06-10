
Pod::Spec.new do |s|
  s.name         = "RNAppNext"
  s.version      = "1.0.0"
  s.summary      = "RNAppNext"
  s.description  = <<-DESC
                  Appnext support for **Android** and comming soon to **iOS**
                   DESC
  s.homepage     = "https://github.com/jerson/react-native-appnext"
  s.license      = "MIT"
  # s.license      = { :type => "MIT", :file => "FILE_LICENSE" }
  s.author             = { "author" => "jeral17@gmail.com" }
  s.platform     = :ios, "7.0"
  s.source       = { :git => "https://github.com/jerson/react-native-appnext.git", :tag => "master" }
  s.source_files  = "RNAppNext/**/*.{h,m}"
  s.requires_arc = true


  s.dependency "React"
  #s.dependency "others"

end

  