import { Component, OnInit } from '@angular/core';
import { DomSanitizer, SafeHtml } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { PostModel } from 'src/app/shared/model/post.model';
import { PostService } from 'src/app/shared/services/post.service';

@Component({
  selector: 'app-post-detail',
  templateUrl: './post-detail.component.html',
  styleUrls: ['./post-detail.component.css'],
})
export class PostDetailComponent implements OnInit {
  post!: PostModel;
  public isLoading = true;
  constructor(
    private activaedRoute: ActivatedRoute,
    private postService: PostService,
    private domSanitizer: DomSanitizer
  ) {}

  ngOnInit(): void {
    this.activaedRoute.params.subscribe((params) => {
      const id = params['id'];
      this.postService.getPostById(id).subscribe((post) => {
        this.post = post;
        this.isLoading = false;
      });
    });
  }

  sanitizeHtml(html: string): SafeHtml {
    // Use DomSanitizer to sanitize HTML content
    return this.domSanitizer.bypassSecurityTrustHtml(html);
  }
}
