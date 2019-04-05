import {Component, OnInit} from '@angular/core';
import {FileUploader} from "ng2-file-upload";
import {AuthService} from "../../auth/auth.service";

const URL = '/api/pending/';

@Component({
  selector: 'entry-edit',
  templateUrl: './entry-edit.component.html',
  styleUrls: ['./entry-edit.component.scss']
})
export class EntryEditComponent implements OnInit {

  // text
  text: string;
  tinyMceSettings = {
    branding: false,
    skin_url: '/assets/tinymce/skins/ui/oxide',
    inline: false,
    statusbar: true,
    browser_spellcheck: true,
    height: 320,
    plugins: 'fullscreen code searchreplace visualblocks visualchars link table',
    toolbar: 'undo redo visualblocks visualchars searchreplace formatselect fontselect fontsizeselect bold italic underline strikethrough blockquote indent outdent alignnone alignleft aligncenter alignright alignjustify subscript superscript link unlink openlink table code fullscreen',
    menubar: false
  };

  // upload
  public hasBaseDropZoneOver: boolean = false;
  public hasAnotherDropZoneOver: boolean = false;
  public uploader: FileUploader;


  constructor(private authService: AuthService) {
    this.uploader = new FileUploader({url: URL});
    this.uploader.options.additionalParameter = {
      type: 'IMAGE',
      text: 'foo'
    };

    authService.token.subscribe(token => {
      this.uploader.authTokenHeader = 'authorization';
      this.uploader.authToken = 'Bearer ' + token;
    })
  }

  public fileOverBase(e: any): void {
    this.hasBaseDropZoneOver = e;
  }

  ngOnInit() {
  }

}
