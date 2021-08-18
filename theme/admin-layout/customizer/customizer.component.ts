import { Component, OnInit, Output, EventEmitter, ViewEncapsulation } from '@angular/core';
import { SettingsService, StartupService } from '@core';
import { CdkDragStart } from '@angular/cdk/drag-drop';

@Component({
  selector: 'app-customizer',
  templateUrl: './customizer.component.html',
  styleUrls: ['./customizer.component.scss'],
  encapsulation: ViewEncapsulation.None,
})
export class CustomizerComponent implements OnInit {
  languages = [
    { value: 'en', label: 'English' },
    { value: 'gn', label: 'German' },
  ];
  options = this.settings.getOptions();
  opened = false;
  dragging = false;
  selectedLanguage: string = "en";

  @Output() optionsEvent = new EventEmitter<object>();

  constructor(private settings: SettingsService, private LangService: StartupService) { }

  ngOnInit() { }

  handleDragStart(event: CdkDragStart): void {
    this.dragging = true;
  }

  setLang(lang: string) {
    this.LangService.use(this.selectedLanguage);
  }

  togglePanel() {
    this.opened = !this.opened;
  }

  openPanel() {
    this.opened = true;
  }

  closePanel() {
    this.opened = false;
  }

  sendOptions() {
    this.optionsEvent.emit(this.options);
  }
}
